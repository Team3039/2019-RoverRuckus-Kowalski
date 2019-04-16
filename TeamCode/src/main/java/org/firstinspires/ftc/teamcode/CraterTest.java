package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Wildcat Robotics Programming Team  on 1/12/2019.
 */

    @Autonomous
    public class CraterTest extends LinearOpMode implements Values {

        private ElapsedTime runtime = new ElapsedTime();
        public DcMotor leftFrontDrive = null;
        public DcMotor rightFrontDrive = null;
        public DcMotor leftBackDrive= null;
        public DcMotor rightBackDrive = null;

        public DcMotor intake = null;
        public DcMotor extension = null;
        public DcMotor arm = null;
        public DcMotor climb= null;


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
            climb = hardwareMap.get(DcMotor.class, "climb");


            leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
            rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
            leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
            rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
            intake.setDirection(DcMotor.Direction.FORWARD);
            extension.setDirection(DcMotor.Direction.FORWARD);
            arm.setDirection(DcMotor.Direction.FORWARD);
            climb.setDirection(DcMotor.Direction.REVERSE);

            waitForStart();
            runtime.reset();

            while (opModeIsActive()) {
                while (getRuntime() <= 14.2) {
                    setClimbPower(.95);
                }
                while (getRuntime() > 14.2 && getRuntime() <= 14.8) {
                    setClimbPower(0);
                }
                while (getRuntime() > 14.8 && getRuntime() <= 17.7) {
                    turnLeft(.55);
                }
                while (getRuntime() > 17.2 && getRuntime() <= 19.2) {
                    turnLeft(0);
                }
                while (getRuntime() > 19.2 && getRuntime() <= 19.35) {
                    drive(-.95);
                }
                while (getRuntime() > 19.7 && getRuntime() <= 19.7) {
                    drive(0);
                }
                while (getRuntime() > 19.7 && getRuntime() <= 20.7) {
                    turnRight(.55);
                }
                while (getRuntime () > 20.7&& getRuntime () <=20.8) {
                    turnRight(0);
                }



                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.update();
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

        public void setClimbPower (double power) {
            climb.setPower(power);
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
            leftFrontDrive.setPower(power);
            rightFrontDrive.setPower(power);
            leftBackDrive.setPower(power);
            rightBackDrive.setPower(power);
        }
        public void turnLeft (double power){
            leftFrontDrive.setPower (power);
            rightFrontDrive.setPower (-power);
            leftBackDrive.setPower (power);
            rightBackDrive.setPower (-power);
        }
        public void turnRight (double power){
            leftFrontDrive.setPower (-power);
            rightFrontDrive.setPower (power);
            leftBackDrive.setPower (-power);
            rightBackDrive.setPower (power);
        }



    }




