package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Launcher extends Thread{
    DcMotorEx main_motor;
    @Override
    public void run() {

    }
    public void obtain_hardware(DcMotorEx mm) {
        main_motor = mm;
    }
    public void set_the_main_motor(float power){
        main_motor.setPower(power);
    }
}