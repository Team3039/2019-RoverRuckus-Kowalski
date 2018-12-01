package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Wildcat Robotics Programming Team  on 10/30/2018.
 */
@TeleOp
public class TeleOpMode extends LinearOpMode implements Values {

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
    public void runOpMode()  {
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

            //Driving
            double leftFrontPower;
            double rightFrontPower;
            double leftBackPower;
            double rightBackPower;
            double armPower;
            double drive = gamepad1.left_stick_y;
            double turn = -gamepad1.right_stick_x;
            double armStick = gamepad2.left_stick_y;

            if (gamepad1.left_bumper) {
                strafeLeft();
            }
            else if (gamepad1.right_bumper) {
                strafeRight();
            }
            else {
                leftFrontPower = Range.clip(drive + turn, -.95, .95);
                rightFrontPower = Range.clip(drive - turn, -.95, .95);
                leftBackPower = Range.clip(drive + turn, -.95, .95);
                rightBackPower = Range.clip(drive - turn, -.95, .95);
                leftFrontDrive.setPower(leftFrontPower);
                rightFrontDrive.setPower(rightFrontPower);
                leftBackDrive.setPower(leftBackPower);
                rightBackDrive.setPower(rightBackPower);
            }

            //Arm Control
            armPower = Range.clip( armStick, -.95, .95 );
            arm.setPower(armPower);

            //Button Inputs
            if (gamepad2.left_trigger >.5) {
                getMineral();
            } else if (gamepad2.right_trigger>.5){
                shootMineral();
            }
            else {
                stopMineral();
            }
            if (gamepad2.y) {
                setIntakePosition(intakeDown);
            }
            else {
                setIntakePosition(intakeStraight);
            }

             if (gamepad1.left_trigger > .5){
                 setExtensionPower(.4);
             }
             else if(gamepad1.right_trigger>.5) {
                 setExtensionPower(-.4);
             }
             else {
                 setExtensionPower(0);
             }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

    }

    public void getMineral() {
        intakeA.setPower(.4);
        intakeB.setPower(.4);
    }

    public void shootMineral() {
        intakeA.setPower(-.4);
        intakeB.setPower(-.4);
    }

    public void stopMineral() {
        intakeA.setPower(0);
        intakeB.setPower(0);
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
        latch.setPosition(-1);
    }

    public void latch() {
        latch.setPosition(1);
    }
    public void strafeRight() {
        leftFrontDrive.setPower(-strafeSpeed);
        leftBackDrive.setPower(strafeSpeed);
        rightFrontDrive.setPower(strafeSpeed);
        rightBackDrive.setPower(-strafeSpeed);
    }

    public void strafeLeft() {
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



}
