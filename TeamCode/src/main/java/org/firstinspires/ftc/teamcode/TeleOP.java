package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class TeleOP extends OpMode{
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor, launcher_motor;
    double y, x, rx;
    byte launcher_speed;
    Drivetrain main_train;
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
    }
    @Override
    public void loop(){
        if (gamepad1.right_trigger > 5.0f && launcher_speed > 0){
            launcher_speed = 10;
        }
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_x;
        main_train.give_power(
            y + x - rx,
            y - x - rx,
            y - x + rx,
            y + x + rx
        );
        main_train.update_power();
    }
}