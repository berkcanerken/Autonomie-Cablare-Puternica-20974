package org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.Opmodes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes.BlueConeDetector;

import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


@Autonomous(name = "ColorDetectionAutonomous", group = "Iuliu")
public class BlueConeDetectorClass extends LinearOpMode {
    OpenCvCamera externalCamera;
    public static BlueConeDetector pipeline;

    @Override
    public void runOpMode() throws InterruptedException {
        initExternalCamera();
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
    public void initExternalCamera() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()
        );

        externalCamera = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "WebcamG"), cameraMonitorViewId
        );
        /*
        TODO when you will use this in a rel progect implement here all piplines

        */

        pipeline = new BlueConeDetector();
        externalCamera.setPipeline(pipeline);

        externalCamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                externalCamera.startStreaming(320, 240, OpenCvCameraRotation.UPSIDE_DOWN);
            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }

}
