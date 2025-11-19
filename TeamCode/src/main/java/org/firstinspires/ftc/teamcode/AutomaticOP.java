package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@TeleOp
public class AutomaticOP extends OpMode {
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor, launch_motor;
    Drivetrain main_train;
    Launcher main_launcher;
    VoltageSensor voltageSensor;
    @Override
    public void init(){
        rb_motor = hardwareMap.get(DcMotorEx.class, "right_back_motor");
        rf_motor = hardwareMap.get(DcMotorEx.class, "right_front_motor");
        lb_motor = hardwareMap.get(DcMotorEx.class, "left_back_motor");
        lf_motor = hardwareMap.get(DcMotorEx.class, "left_front_motor");
        launch_motor = hardwareMap.get(DcMotorEx.class, "launcher_motor");
        main_train = new Drivetrain(new DcMotorEx[]{
                rb_motor,
                rf_motor,
                lb_motor,
                lf_motor
        });
        voltageSensor = hardwareMap.get(VoltageSensor.class, "control_hub");
        main_launcher = new Launcher(launch_motor, voltageSensor);
    }
    @Override
    public void loop(){
        
    }
}