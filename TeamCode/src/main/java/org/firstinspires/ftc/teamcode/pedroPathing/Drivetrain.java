package org.firstinspires.ftc.teamcode.pedroPathing;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

/* Index values for motors
0 - Right Back Motor
1 - Right Front Motor
2 - Left Back Motor
3 - Left Front Motor */

public class Drivetrain {
    public void master_update_power(Gamepad pad, DcMotorEx[] motors){

    }
    public double[] find_powers(double[] val){
        return new double[]{
                val[0] + val[1] - val[2],
                val[0] - val[1] - val[2],
                val[0] - val[1] + val[2],
                val[0] + val[1] + val[2]
        };
    }
    public double[] find_stick_values(Gamepad pad){
        return new double[]{
             pad.left_stick_x,
            -pad.left_stick_y,
             pad.right_stick_x
        };
    }
}
