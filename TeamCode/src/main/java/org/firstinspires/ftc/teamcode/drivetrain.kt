package org.firstinspires.ftc.teamcode

import dev.frozenmilk.dairy.core.dependency.Dependency
import dev.frozenmilk.dairy.core.dependency.annotation.SingleAnnotation
import dev.frozenmilk.mercurial.Mercurial
import dev.frozenmilk.mercurial.subsystems.Subsystem

class drivetrain : Subsystem{
    //IDK What this does
    override var dependency: Dependency<*> = Subsystem.DEFAULT_DEPENDENCY and
            SingleAnnotation(Mercurial.Attach::class.java)

    //I plan on adding basic motor init functions, kinetics, power setting,
    //localisation, dashboard, and pedro stuffs here
}