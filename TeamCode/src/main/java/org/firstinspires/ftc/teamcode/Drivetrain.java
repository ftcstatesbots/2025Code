package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/* Index values for motors
0 - Right Back Motor
1 - Right Front Motor
2 - Left Back Motor
3 - Left Front Motor */

public class Drivetrain {
    DcMotorEx[] object_motors;
    double botHeading;
    IMU imu;
    IMU.Parameters parameters;
    Drivetrain(DcMotorEx[] m, IMU i){
        object_motors = m;
        imu = i;
        parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        ));
        imu.initialize(parameters);
    }
    // A big static update power function that updates power to an array of motors with one command
    public static void static_master_update_power(Gamepad pad, DcMotorEx[] motors){
        static_set_power(find_powers(find_stick_values(pad), 1), motors);
    }
    // A big update power function that updates the power of the object motors with input from a gamepad
    public void master_update_obj_power(Gamepad pad){
        set_obj_motor_power(find_powers(find_stick_values(pad), 1));
    }
    // A SERIES OF SMALLER COMMANDS THAT CAN BE CALLED SEPARATELY

    // The set_power function for the object motors
    public void set_obj_motor_power(double[] powers){
        for (int i = 0; i < 5; i++) object_motors[i].setPower(powers[i]);
    }
    // The static set_power command used for arrays not related to the drivetrain
    public static void static_set_power(double[] powers, DcMotorEx[] motors){
        for (int i = 0; i < 5; i++) motors[i].setPower(powers[i]);
    }

    // A static function that translates a series of stick values to usable power values
    public static double[] find_powers(double[] val, double divisor){
        return new double[]{
                val[0] + val[1] - val[2] / divisor,
                val[0] - val[1] - val[2] / divisor,
                val[0] - val[1] + val[2] / divisor,
                val[0] + val[1] + val[2] / divisor
        };
    }
    // Gives a list of input values given the gamepad
    public static double[] find_stick_values(Gamepad pad){
        return new double[]{
             pad.left_stick_x,
            -pad.left_stick_y,
             pad.right_stick_x
        };
    }

    public void update_rotation(Gamepad gamepad){
        double rotX, rotY, denominator;
        double x = gamepad.left_stick_x, y = gamepad.left_stick_y, rx = gamepad.right_stick_x;
        double[] powers;
        botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        powers = find_powers(new double[]{rotX, rotY, rx}, denominator);
        set_obj_motor_power(powers);
    }
}
