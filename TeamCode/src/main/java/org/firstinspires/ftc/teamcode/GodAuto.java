package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="GodAuto", group="auto")
public class GodAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(this);

        bot.init(hardwareMap);

        waitForStart();

        // # Put sample on Top #
        // forward
        bot.encoderDrive(1.0, 20);
        // strafe left
        bot.encoderStrafe(1.0, 15);
        // pivot arm up
        bot.setArmPos(800); //using tick func
        // extend arm
        bot.setExtendPos(9.75);
        bot.encoderDrive(1,-1);
        // lower pivot arm slightly
        bot.setArmPos(500);
        bot.encoderDrive(1.0, -5);

        bot.setExtendPos(1.0);
        bot.setArmPos(-300);

        // initial strafe for setting up next auto cycle
        bot.encoderStrafe(1.0, 50);

        // # Pickup Cycle and place into high basket
        // loop 3 times
        for(int i = 1; i <= 3; i++) {
            // strafe left
            bot.encoderStrafe(1.0, 10);
            // run intake
            bot.setArmPos(-500);
            // forward
            bot.encoderDrive(1.0, 15);
            bot.runIntake();
            sleep(500);
            bot.stopIntake();
            // backward
            bot.encoderDrive(1.0,-25);
            // pivot turn -145
            bot.encoderTurn(1.0, -145);
            // forward closer to basket
            bot.encoderDrive(1.0, 5);
            // pivot arm
            bot.setArmPos(1280); //0->2560 tick:degree 0->180
            // extend arm
            bot.setExtendPos(15.0);
            // outtake
            bot.runOuttake();
            sleep(500);
            bot.stopIntake();
            // retract arm
            bot.retractArm();
            bot.setArmPos(100);
            // pivot turn 145 and move back
            bot.encoderDrive(1.0, -5);
            bot.encoderTurn(1.0, 145);
            // begin sequence again for the next two blocks
            bot.encoderDrive(1.0, 15);
            //strafe to reallign
            bot.encoderStrafe(1.0, i*10);
        }
    }
}