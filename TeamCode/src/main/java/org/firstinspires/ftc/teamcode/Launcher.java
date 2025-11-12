package org.firstinspires.ftc.teamcode;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Filters.Estimators.Estimator;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
public class Launcher{
    DcMotorEx main_motor;
    public boolean is_on = true;
    public static double Kp = 0.00005f, Ki = 0.0f, Kd = 0.0f, Kf = 0.00038f;
    public static int target_velocity;
    PIDCoefficients coefficients = new PIDCoefficients(Kp, Ki, Kd);
    BasicPID pid = new BasicPID(coefficients);
    Launcher(DcMotorEx m){main_motor = m; main_motor.setDirection(DcMotorSimple.Direction.REVERSE);}
//  double update(double target){
//      return controller.calculate(target, main_motor.getVelocity());
//  }
//  void set_velocity(){
//      main_motor.setPower(update(target_velocity));
//  }
    void setTarget_velocity(int v){
        target_velocity = v;
    }
    void update_velocity(int target){
        double pwr = pid.calculate(target,main_motor.getVelocity())+Kf*main_motor.getVelocity();
        setTarget_velocity(target);
        main_motor.setPower(-pwr);
    }
}