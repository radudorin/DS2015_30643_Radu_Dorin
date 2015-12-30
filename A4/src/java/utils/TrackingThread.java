/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import a4_client.db.PackageTable;
import a4_client.db.RouteTable;
import constants.Constants;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radud
 */
public class TrackingThread extends Thread {

    private int count;
    private final int packageId;
    private final RouteTable routeTable;
    private final PackageTable packageTable;
    private final int MIN = 3000;
    private final int MAX = 4000;
    private final int MAX_RUN = 7;

    public TrackingThread(int packageId, RouteTable routeTable, PackageTable packageTable) {
        this.packageId = packageId;
        this.routeTable = routeTable;
        this.packageTable = packageTable;
        count = 0;
    }

    @Override
    public void run() {

        putToSleep();

        while (count <= MAX_RUN) {
            System.out.println("Running");
            int cityIndex = (int) (1 + Math.random() * Constants.CITIES.length);
            String city = count == MAX_RUN ? packageTable.getPackage(packageId).getDestinationCity() : Constants.CITIES[cityIndex];
            String status = count == MAX_RUN ? Constants.STATUS_RECEIVED : Constants.STATUS_IN_PROGRESS;
            routeTable.updateRoute(packageId, DateUtils.getCurrentTimeStamp(), city, status);
            count++;
            putToSleep();
        }
    }

    private void putToSleep() {
        try {
            this.sleep(MIN + (int) (Math.random() * MAX));
        } catch (InterruptedException ex) {
            Logger.getLogger(TrackingThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
