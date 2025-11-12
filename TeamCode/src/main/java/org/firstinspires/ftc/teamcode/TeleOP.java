package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOP extends OpMode{
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor, launcher_motor;
    double y, x, rx;
    Drivetrain main_train;
    Launcher launcher;
    public boolean trigger_is_pressed(float trigger){
        return trigger >= 5.0f;
    }
    @Override
    public void init(){
        DcMotorEx[] motors = {
            rb_motor = hardwareMap.get(DcMotorEx.class, "right_back_motor"),
            rf_motor = hardwareMap.get(DcMotorEx.class, "right_front_motor"),
            lb_motor = hardwareMap.get(DcMotorEx.class, "left_back_motor"),
            lf_motor = hardwareMap.get(DcMotorEx.class, "left_front_motor")
        };
        launcher_motor = hardwareMap.get(DcMotorEx.class, "launcher_motor");
        main_train = new Drivetrain(motors);
        launcher = new Launcher(launcher_motor);
    }
    @Override
    public void loop(){
        main_train.update_power(gamepad1, true);
        launcher.setTarget_velocity((int) gamepad1.right_trigger * 3000);
    }
}