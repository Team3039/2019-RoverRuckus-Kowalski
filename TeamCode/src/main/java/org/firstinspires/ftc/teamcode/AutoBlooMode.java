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
public class AutoBlooMode extends LinearOpMode implements Values {


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive= null;
    private DcMotor rightBackDrive = null;
    private DcMotor intake = null;
    private DcMotor elevator= null;
    private Servo basket=null;



    @Override
    public void runOpMode() {
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

        }

        while(opModeIsActive() {
            while(getRuntime() < 1.0){
                elevator.setPower(elevatorUp);
            }
            while((getRuntime() > 1.0) && (getRuntime() < 5.0))   {
                leftFrontDrive.setPower(driveSpeed);
                rightFrontDrive.setPower(driveSpeed);
                leftBackDrive.setPower(driveSpeed);
                rightBackDrive.setPower(driveSpeed);
            }
            while((getRuntime() > 5.0) && (getRuntime() < 6.0)) {
                elevator.setPower(elevatorDown);
            }
            while ((getRuntime() > 6.0) && (getRuntime() < 6.5))  {
                leftFrontDrive.setPower(-turningSpeed);
                leftBackDrive.setPower(-turningSpeed);
                rightFrontDrive.setPower(turningSpeed);
                rightBackDrive.setPower(turningSpeed);
            }
            while ((getRuntime() > 6.5) && (getRuntime() < 11.5));
            leftFrontDrive.setPower(driveSpeed);
            rightFrontDrive.setPower(driveSpeed);
            leftBackDrive.setPower(driveSpeed);
            rightBackDrive.setPower(driveSpeed);
            // Modify Time to the actual robot speed per block in the interface for driveSpeed and turnSpeed

        }

        telemetry.addData("Status", "Run Time: " + runtime.toString();
        telemetry.update();
    }
}

