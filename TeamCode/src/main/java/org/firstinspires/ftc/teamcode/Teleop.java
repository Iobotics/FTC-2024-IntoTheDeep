/**
 * Code is based on https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
 * Mechanum drive TeleOp
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Control Mapping
 *
 *  GAMEPAD1 - Drive
 *      Left Joystick X - Strafe
 *      Left Joystick Y - Forward Backward
 *      Right Joystick X - Pivot
 *      Right Trigger - Extend  Arm
 *      Left Trigger - Retract Arm
 *
 *
 *  GAMEPAD2 -
 *
 *  NOTE: Low lift must be completed before high lift is engaged. This is due to rules and the sequencing of getting to
 *      the high bar relies on the bot being already hanging from the low bar.
 */

@TeleOp(name = "TeleOp", group = "TeleOp")
public class Teleop extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Bot bot = new Bot(this);

        double servoFSpeed = 0.55;
        double servoStop = 0.5;
        double servoBSpeed = 0.45;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            // ===== Gamepad 1 =====
            double y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = -gamepad1.left_stick_x; // Counteract imperfect strafing
            double pivot = -gamepad1.right_stick_x;

            double armExtendControl = gamepad1.right_trigger;
            double armRetractControl = gamepad1.left_trigger;

            boolean liftUpControl = gamepad1.b;
            boolean liftDownControl = gamepad1.a;


            // =====================

            // ===== Gamepad 2 =====

            // =====================

            // === DRIVE ===
            //mechanum drive equations for powering each motor
            double frontLeftPower = y + x + pivot;
            double backLeftPower = y - x + pivot;
            double frontRightPower = y - x - pivot;
            double backRightPower = y + x - pivot;

            //normalize power value
            double max = Math.max(Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower)),
                    Math.max(Math.abs(backLeftPower), Math.abs(backRightPower)));

            if (max > 1.0) {
                frontLeftPower /= max;
                frontRightPower /= max;
                backLeftPower /= max;
                backRightPower /= max;
            }
           // bot.setDriveTrain(frontLeftPower, backLeftPower, frontRightPower, backRightPower);

            // === Arm Control ===
            if (armExtendControl > 0.1){
                bot.setServoPos(servoFSpeed);
            } else if(armRetractControl > 0.1) {
                bot.setServoPos(servoBSpeed);
            } else {
                bot.setServoPos(servoStop);
            }

            // === Lift Control ===
            if(liftUpControl){
                bot.setLift(-1100);
            }
            else if (liftDownControl){
                bot.setLift(0);
            }

            // === TELEMETRY ===
            telemetry.addData("Left Front Power: ", frontLeftPower);
            telemetry.addData("Left Back Power: ", backLeftPower);
            telemetry.addData("Right Front Power", frontRightPower);
            telemetry.addData("Right Back Power", backRightPower);
            telemetry.addData("Left Ext Pos: ", bot.getLServoPos());
            telemetry.addData("Right Ext Pos: ", bot.getRServoPos());
            telemetry.addData("Right Lift Pos: ", bot.getRLiftPos());
            telemetry.update();

        }
    }
}