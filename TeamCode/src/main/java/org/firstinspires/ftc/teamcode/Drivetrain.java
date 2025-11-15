package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Drivetrain{
    double rb_power, rf_power, lb_power, lf_power;
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor;
    Drivetrain (DcMotorEx[] motors){
        rb_motor = motors[0];
        rf_motor = motors[1];
        lb_motor = motors[2];
        lf_motor = motors[3];
    }
    void pass_power(double[] powers) {
        rb_power = powers[0];
        rf_power = powers[1];
        lb_power = powers[2];
        lf_power = powers[3];
    }
    void update_power(Gamepad main_pad, boolean autofind){
        if (autofind){pass_power(find_power(main_pad));}
        rb_motor.setPower(rb_power);
        rf_motor.setPower(rf_power);
        lb_motor.setPower(lb_power);
        lf_motor.setPower(lf_power);
    }
    double[] find_power(Gamepad pad){
        double x, y, rx;
        y = -pad.left_stick_y;
        x = pad.left_stick_x;
        rx = pad.right_stick_x;
        return new double[]{
            y + x - rx,
            y - x - rx,
            y - x + rx,
            y + x + rx
        };
    }
    void update_pos(){

    }
}
