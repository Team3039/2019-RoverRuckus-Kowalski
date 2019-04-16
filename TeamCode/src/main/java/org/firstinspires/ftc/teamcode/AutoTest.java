package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@Autonomous
public class AutoTest extends LinearOpMode implements Values {

    private ElapsedTime runtime = new ElapsedTime();

    public Servo test = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        test = hardwareMap.get(Servo.class, "testServo");

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            runtime.reset();

            while(getRuntime() < 28) {
                test.setPosition(1);
            }
//            while(getRuntime() < 2) {
//                test.setPosition(0);
//            }
        }

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }

}


