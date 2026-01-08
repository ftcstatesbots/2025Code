package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class RedAuto extends OpMode {
    DcMotorEx launchM, intakeM;
    Launcher launcher;
    Intake intake;
    Drivetrain drivetrain;
    ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void init() {
        launcher = new Launcher(hardwareMap);
        intake = new Intake(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        drivetrain.init();
    }

    @Override
    public void loop() {
        if(elapsedTime.seconds()<1)drivetrain.setVectorPower(0,-0.3,0.0);
        else if (elapsedTime.seconds()>1&&elapsedTime.seconds()<3)drivetrain.setVectorPower(0,0,0);
        else if (elapsedTime.seconds()>3 && elapsedTime.seconds()<3.2)intake.intakeStep(true);
        else if (elapsedTime.seconds()>4 && elapsedTime.seconds()<4.2)intake.intakeStep(true);
        else if (elapsedTime.seconds()>5 && elapsedTime.seconds()<5.2)intake.intakeStep(true);
        else if (elapsedTime.seconds()>6 && elapsedTime.seconds()<7)drivetrain.setVectorPower(-0.3,0,0);
        else if (elapsedTime.seconds()>7) drivetrain.setVectorPower(0,0,0);
        
        launcher.update_velocity(1500);
        intake.update_pos();
    }

    @Override
    public void start() {
        elapsedTime.reset();
        launcher.setTarget_velocity(1500);

    }
}
