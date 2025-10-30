package com.nextwebsite;// this code is working fine

import javax.imageio.ImageIO;

import org.testng.annotations.Test;

import com.nexauk.Setup;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CaptureScreenShots extends Setup {

    @Test
    public void captureHomePageFullScreenshot() {
        try {
            // Step 1️⃣: Open website
            driver.get("https://www.next.co.uk/");
            System.out.println("🌐 Navigated to: " + driver.getCurrentUrl());

            // Step 2️⃣: Use AShot to capture full-page screenshot
            Screenshot sc = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(1000)) // scroll delay in ms
                    .takeScreenshot(driver);

            // Step 3️⃣: Generate timestamped filename
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String fileName = "FullPage_Next_Homepage" + LocalDateTime.now().format(formatter) + ".png";

            // Step 4️⃣: Define output directory
            String folderPath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "screenshots";
            File folder = new File(folderPath);
            if (!folder.exists()) folder.mkdirs();

            // Step 5️⃣: Save the image file
            File outputFile = new File(folderPath + File.separator + fileName);
            ImageIO.write(sc.getImage(), "PNG", outputFile);

            System.out.println("📸 Full page screenshot captured successfully!");
            System.out.println("🖼️ Screenshot saved at: " + outputFile.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("❌ Error saving screenshot: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
