package org.firstinspires.ftc.teamcode.AutonomieTest.Mara;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.hardware.DcMotor;

public class constants {
    public static double y= -gamepad1.left_stick_y/5;
    public static double x= -gamepad1.left_stick_x/5;
    public static double rx= gamepad1.right_stick_x * 0.5;
    public static double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
    public static double frontLeftPower = (y - x + rx) / denominator;
    public static double backLeftPower = (y + x + rx) / denominator;
    public static double frontRightPower = (y + x - rx) / denominator;
    public static double backRightPower = (y - x - rx) / denominator;
    public static double ServoDS=0.3; //servo deschis stanga
    public static double ServoDD=0.4;
    public static double ServoIS=0.4;
    public static double ServoID=0.6;
    public static DcMotor frontLeftMotor;
    public static DcMotor backLeftMotor;
    public static DcMotor frontRightMotor;
    public static DcMotor backRightMotor;
    }
