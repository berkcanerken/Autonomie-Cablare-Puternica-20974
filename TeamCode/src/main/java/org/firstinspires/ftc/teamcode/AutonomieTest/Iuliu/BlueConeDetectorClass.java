package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu;

import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes.BlueConeDetector;
import org.firstinspires.ftc.teamcode.software.OpenCV.initCamera;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;


@Autonomous(name = "ColorDetectionAutonomous", group = "Iuliu")
public class BlueConeDetectorClass extends LinearOpMode {
    OpenCvCamera externalCamera;
    public static BlueConeDetector pipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        initCamera camera = new initCamera();
        camera.initExternalCamera();
        externalCamera = camera.externalCamera;
        pipeline = new BlueConeDetector();


        waitForStart();

        while (opModeIsActive()) {
            if (pipeline.isBlueDetected())
            {
                servo.setPosition(1);
            } else
            {
                servo.setPosition(0);
            }


            ComposeTelementry();
            telemetry.update();
        }

        if(isStopRequested())return;
    }
    private void ComposeTelementry()
    {
        Point squareCenter = pipeline.getSquareCenter();
        telemetry.addData("Square Center X", squareCenter.x); // Display X coordinate
        telemetry.addData("Square Center Y", squareCenter.y); // Display Y coordinate
        telemetry.addData("Servo Position", servo.getPosition());
    }

}
