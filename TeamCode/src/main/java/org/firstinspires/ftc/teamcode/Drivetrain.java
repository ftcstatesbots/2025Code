package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

/* Index values for motors
0 - Right Back Motor
1 - Right Front Motor
2 - Left Back Motor
3 - Left Front Motor */

public class Drivetrain {
    // A big update power function that updates power with one command
    public void master_update_power(Gamepad pad, DcMotorEx[] motors){
        set_power(find_powers(find_stick_values(pad)), motors);
    }
    // A SERIES OF SMALLER COMMANDS THAT CAN BE CALLED SEPARATELY

    // Small set power command
    public void set_power(double[] powers, DcMotorEx[] motors){
        for (int i = 0; i < 5; i++) motors[i].setPower(powers[i]);
    }

    // Translates a series of stick values to usable power values
    public double[] find_powers(double[] val){
        return new double[]{
                val[0] + val[1] - val[2],
                val[0] - val[1] - val[2],
                val[0] - val[1] + val[2],
                val[0] + val[1] + val[2]
        };
    }
    // Gives a list of input values given the gamepad
    public double[] find_stick_values(Gamepad pad){
        return new double[]{
             pad.left_stick_x,
            -pad.left_stick_y,
             pad.right_stick_x
        };
    }
}
