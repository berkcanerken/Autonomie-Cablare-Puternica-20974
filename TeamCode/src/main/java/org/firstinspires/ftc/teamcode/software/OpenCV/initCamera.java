package org.firstinspires.ftc.teamcode.software.OpenCV;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.Opmodes.BlueConeDetectorClass.pipeline;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.AutonomieTest.Iuliu.BlueConeDetector.Classes.BlueConeDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


 class initCamera {
     public OpenCvCamera externalCamera;


    private void initExternalCamera() {
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
