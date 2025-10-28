package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Custom_PID_controller {
    public double get_rpm(DcMotorEx motor){return motor.getVelocity() / 28.0f;}

    public double get_correct_power(DcMotorEx motor, double target_rpm){
        double difference = target_rpm - get_rpm(motor);
        boolean is_pos = (difference > 0);
        difference = Math.abs(difference);
        if (is_pos){

            return motor.getPower() - Math.pow(2.0f,difference) - 1;
        } else {
            return motor.getPower() + Math.pow(2.0f,difference) - 1;
        }
    }
}
