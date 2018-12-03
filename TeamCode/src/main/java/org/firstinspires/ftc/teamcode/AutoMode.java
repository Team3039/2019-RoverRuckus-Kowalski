package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@Autonomous
public class AutoMode extends LinearOpMode implements Values {

    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive= null;
    public DcMotor rightBackDrive = null;

    public DcMotor intakeA = null;
    public DcMotor intakeB = null;

    public DcMotor extension = null;
    public DcMotor arm = null;
    public Servo intakePivot = null;
    public Servo latch = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftRearMotor");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightRearMotor");
        intakeA = hardwareMap.get(DcMotor.class, "intakeA");
        intakeB = hardwareMap.get(DcMotor.class, "intakeB");
        extension = hardwareMap.get (DcMotor.class, "extension");
        arm = hardwareMap.get (DcMotor.class, "arm");
        intakePivot = hardwareMap.get (Servo.class, "intakePivot");
        latch = hardwareMap.get(Servo.class, "latch");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intakeA.setDirection(DcMotor.Direction.FORWARD);
        intakeB.setDirection (DcMotor.Direction.REVERSE);
        extension.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection (DcMotor.Direction.FORWARD);
        intakePivot.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            runtime.reset();

            while(getRuntime() < 5) {
                delatch ();
                setArmPower (.8);
            }

            while (getRuntime()<9) {
                setArmPower(-.5);
            }
            while (getRuntime()<15) {
                setExtensionPower(-.95);
                setArmPower(0);
            }
            while (getRuntime()<20){
                setExtensionPower(0);
                strafeLeft();
            }
//            delatch();
//            sleep(2000);
//            setArmPower(.4);
//            sleep(750);
//            setArmPower(0);
//            setExtensionPower(.9);
//            setIntakePosition(intakeStraight);
//            sleep(2750);
//            setExtensionPower(0);
//            strafeLeft();
//            sleep(250);
//            drive(0);
//            sleep(100);
//            drive(.8);
//            sleep(125);
//            drive(0);
//            sleep(100);
//            strafeRight();
//            sleep(250);
//            drive(.7);
//            sleep(350);
//            shootMineral();
//            sleep(500);
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

    public void getMineral() {
        intakeA.setPower(.4);
        intakeB.setPower(.4);
    }

    public void shootMineral() {
        intakeA.setPower(-.4);
        intakeB.setPower(-.4);
    }

    public void setIntakePosition(double position) {
        intakePivot.setPosition(position);
    }

    public void setExtensionPower(double power) {
        extension.setPower(power);
    }

    public void setArmPower(double power) {
        arm.setPower(power);
    }

    public void delatch() {
        latch.setPosition(.25);
    }

    public void latch() {
        latch.setPosition(0);
    }
    public void strafeLeft() {
        leftFrontDrive.setPower(-strafeSpeed);
        leftBackDrive.setPower(strafeSpeed);
        rightFrontDrive.setPower(strafeSpeed);
        rightBackDrive.setPower(-strafeSpeed);
    }

    public void strafeRight() {
        rightFrontDrive.setPower(-strafeSpeed);
        rightBackDrive.setPower(strafeSpeed);
        leftFrontDrive.setPower(strafeSpeed);
        leftBackDrive.setPower(-strafeSpeed);
    }

    public void drive(double power) {
        leftFrontDrive.setPower(-power);
        rightFrontDrive.setPower(power);
        leftBackDrive.setPower(-power);
        rightBackDrive.setPower(power);
    }
    public void turnRight (double power){
        leftFrontDrive.setPower (power);
        rightFrontDrive.setPower (-power);
        leftBackDrive.setPower (power);
        rightBackDrive.setPower (-power);
    }
    public void turnLeft (double power){
        leftFrontDrive.setPower (-power);
        rightFrontDrive.setPower (power);
        leftBackDrive.setPower (-power);
        rightBackDrive.setPower (power);
    }


}


