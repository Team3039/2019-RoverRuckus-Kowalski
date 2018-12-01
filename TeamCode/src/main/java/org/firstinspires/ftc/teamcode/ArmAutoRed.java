package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class ArmAutoRed extends TeleOpMode implements Values {

    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive= null;
    public DcMotor rightBackDrive = null;

    public DcMotor intake = null;
    public DcMotor intake2 = null;

    public DcMotor extension = null;
    public DcMotor arm = null;
    public Servo intakePivot = null;





    @Override
    public void runOpMode() {
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
        intakePivot = hardwareMap.get (Servo.class, "intakePivot");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intake2.setDirection (DcMotor.Direction.FORWARD);
        extension.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection (DcMotor.Direction.FORWARD);
        intakePivot.setDirection(Servo.Direction.FORWARD);



        waitForStart();
        runtime.reset();




        while (opModeIsActive()) {

            while (getRuntime() < .75) {
                arm.setPower (.4);
                extension.setPower(0.4);

                //arm extends forward and robot descends from moon lander.
            }
            while ((getRuntime()>.75) && (getRuntime()< 1)){
                arm.setPower(.4);
                leftFrontDrive.setPower(-strafeSpeed);
                leftBackDrive.setPower(strafeSpeed);
                rightFrontDrive.setPower(strafeSpeed);
                rightBackDrive.setPower(-strafeSpeed);
            }
            while ((getRuntime() > 1) && (getRuntime() < 1.25)) {
                leftFrontDrive.setPower(-turningSpeed);
                rightFrontDrive.setPower(turningSpeed);
                leftBackDrive.setPower(-turningSpeed);
                rightBackDrive.setPower(turningSpeed);
            }
            while ((getRuntime() > 1) && (getRuntime() < 1.25)) {
              arm.setPower (-.4);
              intakePivot.setPosition(0);
            }
            while ((getRuntime() > 1.25) && (getRuntime() < 1.5   )) {
                leftFrontDrive.setPower(driveSpeed);
                leftBackDrive.setPower(driveSpeed);
                rightFrontDrive.setPower(driveSpeed);
                rightBackDrive.setPower(driveSpeed);
            }
            while ((getRuntime() > 1.5) && (getRuntime() <1.75 )) {
                leftFrontDrive.setPower(turningSpeed);
                rightFrontDrive.setPower(-turningSpeed);
                leftBackDrive.setPower(turningSpeed);
                rightBackDrive.setPower(-turningSpeed);
            }
            while ((getRuntime() >1.75)&& (getRuntime() <3.75)){
                leftFrontDrive.setPower(driveSpeed);
                leftBackDrive.setPower(driveSpeed);
                rightFrontDrive.setPower(driveSpeed);
                rightBackDrive.setPower(driveSpeed);
            }
            while ((getRuntime () > 3.75) && (getRuntime () <4.25 )){
                intake.setPower (-.4);
                intake2.setPower (-.4);
            }
            while ((getRuntime () >4.25) && (getRuntime () < 4.5)); {
                leftFrontDrive.setPower(-turningSpeed);
                rightFrontDrive.setPower(turningSpeed);
                leftBackDrive.setPower(-turningSpeed);
                rightBackDrive.setPower(turningSpeed);
            }
            while ((getRuntime () >4.5) && (getRuntime () < 7.5)){
                leftFrontDrive.setPower(driveSpeed);
                leftBackDrive.setPower(driveSpeed);
                rightFrontDrive.setPower(driveSpeed);
                rightBackDrive.setPower(driveSpeed);
            }

            // Modify Time to the actual robot speed per block in the interface for driveSpeed and turningSpeed




        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}

