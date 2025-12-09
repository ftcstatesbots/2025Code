package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

/* Index values for motors
0 - Right Back Motor
1 - Right Front Motor
2 - Left Back Motor
3 - Left Front Motor */

public class Drivetrain {
    DcMotorEx[] object_motors;
    Drivetrain(DcMotorEx[] m){
        object_motors = m;
    }
    // A big static update power function that updates power to an array of motors with one command
    public static void static_master_update_power(Gamepad pad, DcMotorEx[] motors){
        static_set_power(find_powers(find_stick_values(pad)), motors);
    }
    // A big update power function that updates the power of the object motors with input from a gamepad
    public void master_update_obj_power(Gamepad pad){
        set_obj_motor_power(find_powers(find_stick_values(pad)));
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
    public static double[] find_powers(double[] val){
        return new double[]{
                val[0] + val[1] - val[2],
                val[0] - val[1] - val[2],
                val[0] - val[1] + val[2],
                val[0] + val[1] + val[2]
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
}
