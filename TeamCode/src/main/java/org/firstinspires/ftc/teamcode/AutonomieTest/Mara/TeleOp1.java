package org.firstinspires.ftc.teamcode.AutonomieTest.Mara;

import static org.firstinspires.ftc.teamcode.AutonomieTest.Mara.constants.*;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servol;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive;

@TeleOp(name="TeleOpMara")
public class TeleOp1 extends LinearOpMode {

    SampleMecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {

        drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        //ServoDS, ServoDD, ServoIS, ServoID, PowerX, PowerY
        while(opModeIsActive())
        {
            //gamepad1 sa foloseasca miscarea bratului si a servourilor
            //gamepad2 sa foloseasca miscarea robotului
            if(gamepad1.a)
            {
                servol.setPosition(ServoDS);
                servor.setPosition(ServoDD);
            }
            if(gamepad1.b)
            {
                servol.setPosition(ServoIS);
                servor.setPosition(ServoID);
            }
            if(gamepad2.dpad_up)
            {
                PowerY=0.5;
            }
            if(gamepad2.dpad_down)
            {
                PowerY=-0.5;
            }
            if(gamepad2.dpad_right)
            {
                PowerX=0.5;
            }
            if(gamepad2.dpad_left)
            {
                PowerX=-0.5;
            }
        }

        if(isStopRequested())
        {
            return;
        }
    }
}
