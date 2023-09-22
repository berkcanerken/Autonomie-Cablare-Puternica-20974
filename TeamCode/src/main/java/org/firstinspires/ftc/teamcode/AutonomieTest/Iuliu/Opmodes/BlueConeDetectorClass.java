package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.Opmodes;

import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servo;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes.BlueConeDetector;
import org.firstinspires.ftc.teamcode.software.OpenCV.initCamera;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "ColorDetectionAutonomous", group = "Iuliu")
public class BlueConeDetectorClass extends LinearOpMode {
    OpenCvCamera Camera1;
    BlueConeDetector pipeline;
    @Override
    public void runOpMode() throws InterruptedException {
        initCamera camera = new initCamera(hardwareMap);
        Camera1 = camera.externalCamera;
        pipeline = new BlueConeDetector();

        waitForStart();

        while (opModeIsActive()) {
            if (pipeline.isBlueDetected()) {
                servo.setPosition(1);
            } else {
                servo.setPosition(0);
            }

            Point squareCenter = pipeline.getSquareCenter();
            telemetry.addData("Square Center X", squareCenter.x);
            telemetry.addData("Square Center Y", squareCenter.y);
            telemetry.addData("Servo Position", servo.getPosition());

            telemetry.update();
        }
    }
}
