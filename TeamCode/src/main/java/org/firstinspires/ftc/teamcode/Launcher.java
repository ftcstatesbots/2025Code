package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Launcher{
    boolean PIDEnabled = false;
    DcMotorEx main_motor;
    double main_motor_power;
    double main_motor_speed_target;
    boolean is_on = true;
    Custom_PID_controller controller = new Custom_PID_controller();
    Launcher(DcMotorEx m){main_motor = m;}

    public void setMain_motor_power(double p){
        main_motor_power = p;
    }
    public void setMain_motor_speed_target(double r){
        main_motor_speed_target = r;
    }
    public void start_running(){
        while (is_on){

        }
    }

    public void update(){
        if(PIDEnabled){
            main_motor_power = controller.get_correct_power(main_motor, main_motor_speed_target);
        }
        setMain_motor_power(main_motor_power);
    }
}