package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class AutomaticOP extends OpMode {
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor, launch_motor;
    Drivetrain main_train;
    Launcher main_launcher;
    VoltageSensor voltageSensor;
    ElapsedTime et;
    @Override
    public void init(){
        launch_motor = hardwareMap.get(DcMotorEx.class, "launcher_motor");
        main_train = new Drivetrain(hardwareMap);
        main_train.init();
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        main_launcher = new Launcher(hardwareMap);
    }

    @Override
    public void loop(){
        if (et.seconds()<3){
            main_train.setVectorPower(0,0.2,0);
        } else {
            main_train.setVectorPower(0,0,0);
        }
    }
    @Override
    public void start(){
        et = new ElapsedTime();
    }
}