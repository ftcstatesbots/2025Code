package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain{
    HardwareMap hardwareMap;
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor;

    Drivetrain(HardwareMap hw){hardwareMap = hw;}

    public void setPowers(double rb,double rf,double lb,double lf){
        rb_motor.setPower(rb);
        rf_motor.setPower(rf);
        lb_motor.setPower(lb);
        lf_motor.setPower(lf);
    }

    public void setVectorPower(double x, double y, double r){
        setPowers(
            y-x-r,
            y+x-r,
            y-x+r,
            y+x+r
        );
    }

    public void init(){
        rb_motor = hardwareMap.get(DcMotorEx.class, "right_back_motor");
        rf_motor = hardwareMap.get(DcMotorEx.class, "right_front_motor");
        lb_motor = hardwareMap.get(DcMotorEx.class, "left_back_motor");
        lf_motor = hardwareMap.get(DcMotorEx.class, "left_front_motor");

        rb_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lb_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        lf_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        rb_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        rf_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
