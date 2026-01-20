package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Drivetrain{
    HardwareMap hardwareMap;
    DcMotorEx rb_motor, rf_motor, lb_motor, lf_motor;
    IMU imu;

    double heading = 0;

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

    public void setFSVectorPower(double x, double y, double r){
        heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double rotX = (x * Math.cos(-heading) - y * Math.sin(-heading))*1.1;
        double rotY = x * Math.sin(-heading) + y * Math.cos(-heading);
        double d = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(r), 1);
        setVectorPower(rotX/d,rotY/d,r/d);
    }

    public void resetHeading(){
        imu.resetYaw();
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

        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));
        imu.initialize(parameters);
    }
}
