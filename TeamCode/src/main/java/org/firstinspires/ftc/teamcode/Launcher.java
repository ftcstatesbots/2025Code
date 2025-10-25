package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher extends Thread{
    DcMotorEx main_motor;
    double target_speed;
    double main_motor_power;
    public void reset(){
        target_speed = 0;
    }
    public void set_speed(double speed){
        target_speed = speed;
    }
    public double get_rpm(){
        return main_motor.getVelocity() / 28.0f;
    }
    public void update(){
        double difference = target_speed - get_rpm();
        if (difference < 0){
            difference = Math.abs(difference);
            main_motor_power += Math.pow(2.0f,difference) - 1;
        } else if (difference > 0){
            difference = Math.abs(difference);
            main_motor_power -= Math.pow(2.0f,difference) - 1;
        }
        main_motor.setPower(main_motor_power);
    }
    @Override
    public void run(){

    }
}