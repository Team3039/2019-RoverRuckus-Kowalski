package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class ArmAutoRed extends TeleOpMode implements Values {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive= null;
    private DcMotor rightBackDrive = null;
    private DcMotor intake = null;
    private DcMotor extension = null;
    private DcMotor pivot = null;





    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        extension = hardwareMap.get (DcMotor.class, "extension");
        pivot = hardwareMap.get (DcMotor.class, "pivot");

        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        extension.setDirection(DcMotor.Direction.FORWARD);



        waitForStart();
        runtime.reset();




        while (opModeIsActive()) {
            while (getRuntime() < 1.0) {
                extension.setPower(0.4);
                pivot.setPower(.4);
                //arm extends forward and robot descends from moon lander. intake comes down
            }
            while ((getRuntime() > 1.0) && (getRuntime() < 5.0)) {
                leftFrontDrive.setPower(driveSpeed);
                rightFrontDrive.setPower(driveSpeed);
                leftBackDrive.setPower(driveSpeed);
                rightBackDrive.setPower(driveSpeed);
                //drives forward
            }
            while ((getRuntime() > 5.0) && (getRuntime() < 6.0)) {
                extension.setPower(-.4);
                //extension part comes down
            }
            while ((getRuntime() > 6.0) && (getRuntime() < 6.5)) {
                leftFrontDrive.setPower(-turningSpeed);
                leftBackDrive.setPower(-turningSpeed);
                rightFrontDrive.setPower(turningSpeed);
                rightBackDrive.setPower(turningSpeed);
                // It turns left
            }
            while ((getRuntime() > 6.5) && (getRuntime() < 8.5)) ;
            leftFrontDrive.setPower(driveSpeed);
            rightFrontDrive.setPower(driveSpeed);
            leftBackDrive.setPower(driveSpeed);
            rightBackDrive.setPower(driveSpeed);
            // Modify Time to the actual robot speed per block in the interface for driveSpeed and turningSpeed




        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}

