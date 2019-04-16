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

    public DcMotor intake = null;
    public DcMotor climb = null;
    public DcMotor extension = null;
    public DcMotor arm = null;




    @Override
    public void runOpMode()  {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontMotor");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontMotor");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftRearMotor");
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightRearMotor");
        intake = hardwareMap.get(DcMotor.class, "intake");
        extension = hardwareMap.get (DcMotor.class, "extension");
        arm = hardwareMap.get (DcMotor.class, "arm");
        climb = hardwareMap.get (DcMotor.class, "climb");



        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        extension.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection (DcMotor.Direction.FORWARD);
        climb.setDirection (DcMotor.Direction.REVERSE);

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
            double turn = -gamepad1.right_stick_x*.85 ;
            double armStick = gamepad2.right_stick_y;


            if (gamepad1.left_bumper) {
                strafeLeft();
                //Press left bumper to strafe left
            }
            else if (gamepad1.right_bumper) {
                strafeRight();
                //Press right bumper to strafe right
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
            if (gamepad1.a) {
                getMineral();
                //Press to suck in Mineral
            } else if (gamepad1.b){
                shootMineral();
                //Press to shoot the Mineral
            }
            else {
                stopMineral();
            }
             if (gamepad2.left_trigger > .5){
                 setExtensionPower(.9);
                 //Press left trigger to move the extension power up
             }
             else if(gamepad2.right_trigger>.5) {
                 setExtensionPower(-.9);
                 //Press right trigger to move the extension down
             }
             else {
                 setExtensionPower(0);
             }

             //Climbing
            if (gamepad1.dpad_up) {
                 setClimbPower (.95);
                 //Move the d-pad upwards to climb upwards
            }
            else if (gamepad1.dpad_down){
                 setClimbPower (-.95);
                 //Move the d-pad downwards to climb downwards
            }
            else{
                setClimbPower (0);
            }







            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData ( "Latch Position",  +.25);
            telemetry.update();
        }

    }

    public void getMineral() {
        intake.setPower(.5);
    }

    public void shootMineral() {
        intake.setPower(-.5);
    }

    public void stopMineral() {
        intake.setPower(0);
    }



    public void setExtensionPower(double power) {
        extension.setPower(power);
    }

    public void setArmPower(double power) {arm.setPower(power);}

    public void setClimbPower (double power) {climb.setPower (power);}


    public void strafeRight() {
        leftFrontDrive.setPower(-strafeSpeed);
        leftBackDrive.setPower(strafeSpeed);
        rightFrontDrive.setPower(strafeSpeed);
        rightBackDrive.setPower (-strafeSpeed);
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
