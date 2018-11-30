package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp", group="OpMode")
@Disabled
public class ElevatorOpMode extends LinearOpMode implements Values  {


    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive= null;
    public DcMotor rightBackDrive = null;
    public DcMotor intake = null;
    public DcMotor elevator= null;
    public Servo basket=null;


    @Override
    public void runOpMode()  {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        elevator = hardwareMap.get(DcMotor.class, "elevator");
        basket = hardwareMap.get(Servo.class, "basket");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        elevator.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        runtime.reset();

        basket.setPosition(basketLevel);


        while (opModeIsActive()) {

            //Driving
            double leftFrontPower;
            double rightFrontPower;
            double leftBackPower;
            double rightBackPower;
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;


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
                // If the pilot were to press the A button the robot would move at a speed of .4
            } else {
                intake.setPower(0);
                // If the pilot does not press the a button then the robot will not move.
            }

            if (gamepad2.left_bumper) {
                elevator.setPower(elevatorUp);
            }// If the pilot were to press the left bumper the elevator will go up
            else if (gamepad2.right_bumper) {
                elevator.setPower(elevatorDown);
                // If the pilot presses the right bumper the elevator will go to the down
            } else {
                elevator.setPower(0.0);
                // If the pilot does not press the right bumper the elevator will not move right now
            }
            //If not the elevator will not move
            if (gamepad2.y) {
                basket.setPosition(basketAngle);
                //If the pilot presses the y button then the basket will move to a set angle
            }
            //
            else {
                basket.setPosition(basketLevel);
                // If the pilot does not press the y button then the basket will stay at a n already specified level

            }

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
