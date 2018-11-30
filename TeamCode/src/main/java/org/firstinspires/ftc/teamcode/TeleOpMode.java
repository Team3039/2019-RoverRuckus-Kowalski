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
    public DcMotor intake2 = null;
    public DcMotor extension = null;
    public DcMotor arm = null;
    public Servo pivot = null;




    @Override
    public void runOpMode()  {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake2 = hardwareMap.get(DcMotor.class, "intake2");
        extension = hardwareMap.get (DcMotor.class, "extension");
        arm = hardwareMap.get (DcMotor.class, "arm");
        pivot = hardwareMap.get (Servo.class, "pivot");



        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intake2.setDirection (DcMotor.Direction.FORWARD);
        extension.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection (DcMotor.Direction.FORWARD);
        pivot.setDirection(Servo.Direction.FORWARD);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {

            //Driving
            double leftFrontPower;
            double rightFrontPower;
            double leftBackPower;
            double rightBackPower;
            double armPower;
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x * -1;
            double armStick = gamepad2.right_stick_y;

            if (gamepad1.left_bumper) {
                leftFrontDrive.setPower(-strafeSpeed);
                leftBackDrive.setPower(strafeSpeed);
                rightFrontDrive.setPower(strafeSpeed);
                rightBackDrive.setPower(-strafeSpeed);
            } else if (gamepad1.right_bumper) {
                rightFrontDrive.setPower(-strafeSpeed);
                rightBackDrive.setPower(strafeSpeed);
                leftFrontDrive.setPower(strafeSpeed);
                leftBackDrive.setPower(-strafeSpeed);
            }// If the pilot presses the right bumper the robot will strafe to the right
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

            if (gamepad2.a) {
                intake.setPower(.4);
                intake2.setPower (.4);
                // If the pilot were to press the A button the robot would move forward at a speed of .4
            } else if (gamepad2.x){
                intake.setPower(-.4);
                intake2.setPower (-.4);
                // If the pilot does not press the a button then the robot will not move.
            }
            if (gamepad2.left_bumper) {
                pivot.setPosition(-.5);
            }
            else if (gamepad2.right_bumper) {
                pivot.setPosition(.5);
            }
            else {
                pivot.setPosition(0);

            }



             armPower = Range.clip( armStick, -.95, .95 );
             arm.setPower(armPower);

             if (gamepad1.left_trigger > .5){
                 extension.setPower (.4);
             } else if(gamepad1.right_trigger>.5) {
                  extension.setPower (-.4);
               } else {
                 extension.setPower(0);
              }




            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }

    }

}
