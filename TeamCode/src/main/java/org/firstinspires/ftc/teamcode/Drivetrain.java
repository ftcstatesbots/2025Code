package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Drivetrain{
    double rb_power, rf_power, lb_power, lf_power;
    DcMotor rb_motor, rf_motor, lb_motor, lf_motor;
    Drivetrain (DcMotor[] motors){
        rb_motor = motors[0];
        rf_motor = motors[1];
        lb_motor = motors[2];
        lf_motor = motors[3];
    }
    void give_power(double rb, double rf, double lb, double lf){
        rb_power = rb;
        rf_power = rf;
        lb_power = lb;
        lf_power = lf;
    }
    void update_power() {
        rb_motor.setPower(rb_power);
        rf_motor.setPower(rf_power);
        lb_motor.setPower(lb_power);
        lf_motor.setPower(lf_power);
    }
}
