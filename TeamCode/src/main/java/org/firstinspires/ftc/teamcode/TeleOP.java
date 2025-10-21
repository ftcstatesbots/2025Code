package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class TeleOP extends OpMode{
    DcMotor rb_motor, rf_motor, lb_motor, lf_motor;
    double y, x, rx;
    byte launcher_speed;
    @Override
    public void init(){
        rb_motor = hardwareMap.get(DcMotor.class, "right_back_motor");
        rf_motor = hardwareMap.get(DcMotor.class, "right_front_motor");
        lb_motor = hardwareMap.get(DcMotor.class, "left_back_motor");
        lf_motor = hardwareMap.get(DcMotor.class, "left_front_motor");
    }
    @Override
    public void loop(){
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rx = gamepad1.right_stick_x;
        lf_motor.setPower(y + x + rx);
        lb_motor.setPower(y - x + rx);
        rf_motor.setPower(y - x - rx);
        rb_motor.setPower(y + x - rx);
    }
}