package org.firstinspires.ftc.teamcode

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID
import com.ThermalEquilibrium.homeostasis.Controllers.Feedforward.BasicFeedforward
import com.ThermalEquilibrium.homeostasis.Filters.Estimators.RawValue
import com.ThermalEquilibrium.homeostasis.Parameters.FeedforwardCoefficients
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients
import com.ThermalEquilibrium.homeostasis.Systems.BasicSystem
import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotorEx
import dev.frozenmilk.dairy.core.FeatureRegistrar
import dev.frozenmilk.dairy.core.dependency.Dependency
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation
import dev.frozenmilk.mercurial.Mercurial
import dev.frozenmilk.mercurial.subsystems.Subsystem
import java.util.function.DoubleSupplier


@Config
class Launcher : Subsystem {
    //configure motor so we can use it in the subsystem without passing it in or waiting for init
    //anti "null object referanced" or wtv
    private val motor by subsystemCell{
        FeatureRegistrar.activeOpMode.hardwareMap.get(DcMotorEx::class.java, "launchMotor")
    }


    @JvmField
    public var PIDCoefficients = PIDCoefficients(0.0,0.0,0.0)
    @JvmField
    public var FeedforwardCoefficients = FeedforwardCoefficients(0.0,0.0,0.0)
    private var PID = BasicPID(PIDCoefficients)
    private var FF = BasicFeedforward(FeedforwardCoefficients)

    var motorVelocity = DoubleSupplier { motor.getVelocity() }
    var noFilter = RawValue(motorVelocity)
    var system = BasicSystem(noFilter,PID,FF)

    //IDK What this does
    override var dependency: Dependency<*> = Subsystem.DEFAULT_DEPENDENCY and
            SingleAnnotation(Mercurial.Attach::class.java)

    //I plan on making a PIDF controller here for velocity. Maybe also a launcher power calculation


}