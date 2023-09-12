package org.firstinspires.ftc.teamcode.AutonomieTest.Mara;

import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.PowerX;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.PowerY;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.ServoDD;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.ServoDS;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.ServoID;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.ServoIS;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.sensor;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servol;
import static org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive.servor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.TuningAndTrajectories.TuningConstants.SampleMecanumDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOpMara")
public class TeleOp extends LinearOpMode {

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
