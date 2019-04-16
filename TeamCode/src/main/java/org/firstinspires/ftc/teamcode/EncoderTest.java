package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Wildcat Robotics Programming Team  on 12/4/2018.
 */

@Autonomous
public class EncoderTest extends LinearOpMode implements Values {
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive= null;
    public DcMotor rightBackDrive = null;

    public DcMotor intake = null;
    public DcMotor extension = null;
    public DcMotor arm = null;
    public DcMotor climb = null;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftRearMotor");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightRearMotor");
        intake = hardwareMap.get(DcMotor.class, "intake");
        extension = hardwareMap.get(DcMotor.class, "extension");
        arm = hardwareMap.get(DcMotor.class, "arm");
        climb = hardwareMap.get (DcMotor.class, "climb");


        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        extension.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);
        climb.setDirection (DcMotor.Direction.REVERSE);

        //resets encoder count kept by leftFrontDrive
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //sets leftFrontDrive to run to a the the target encoder position
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //although this is the default mode, sets other motors to run without any regard to an encoder
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        //waits for the start button to be pressed
        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        //gives leftFrontDrive a target count (560= 2 rotations)
        leftFrontDrive.setTargetPosition(560);

        //set power to all motors in order to star movement
        leftFrontDrive.setPower(-.25);
        rightFrontDrive.setPower(-.25);
        leftBackDrive.setPower(-.25);
        rightBackDrive.setPower(-.25);

        //wait while opmode is active and leftFrontDrive is busy running to position
        while (opModeIsActive() && leftFrontDrive.isBusy()) {

            telemetry.addData("encoder-fwd", leftFrontDrive.getCurrentPosition() + "busy=" + leftFrontDrive.isBusy());
            telemetry.update();
            idle();


        }

//        telemetry.addData("Status", "Run Time: " + runtime.toString());



        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        leftFrontDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);

        // wait 5 sec to you can observe the final encoder position.

        resetStartTime();

        while (opModeIsActive() && getRuntime() < 5) {
            telemetry.addData("encoder-fwd-end", leftFrontDrive.getCurrentPosition() + "  busy=" + leftFrontDrive.isBusy());
            telemetry.update();
            idle();
        }
    }



    public void getMineral() {
        intake.setPower(.4);
    }

    public void shootMineral() {
        intake.setPower(-.4);
    }



    public void setExtensionPower(double power) {
        extension.setPower(power);
    }

    public void setArmPower(double power) {
        arm.setPower(power);
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