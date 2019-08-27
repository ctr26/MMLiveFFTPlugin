/**
 * Binding to ClearVolume 3D viewer View Micro-Manager datasets in 3D
 *
 * AUTHOR: Nico Stuurman COPYRIGHT: Regents of the University of California,
 * 2015
 * LICENSE: This file is distributed under the BSD license. License text is
 * included with the source distribution.
 *
 * This file is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.
 *
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES.
 */

package org.npl.biomet.mmsim;

import java.util.List;
import org.micromanager.MenuPlugin;
import org.micromanager.Studio;
import org.micromanager.data.*;
import org.micromanager.display.DisplayGearMenuPlugin;

import org.micromanager.display.DisplayWindow;
//import org.micromanager.api.DisplayWindow;
//import org.micromanager.display.InspectorPanel;
//import org.micromanager.display.InspectorPlugin;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.SciJavaPlugin;

/**
 * More or less boiler plate code to become a Micro-Manager 2.0 plugin
 * Most of the action happens in the CVViewer class
 * @author nico
 */
@Plugin(type = MenuPlugin.class)
public class MMLiveFFTPlugin implements MenuPlugin, SciJavaPlugin {

   private Studio studio_;
   static public final String VERSION_INFO = "0.0.1";
   static private final String COPYRIGHT_NOTICE = "Copyright by UCSF, 2015-2017";
   static private final String DESCRIPTION = "MMLiveFFTPlugin";
   static private final String NAME = "MMLiveFFTPlugin";

   @Override
   public void setContext(Studio studio) {
      studio_ = studio;
   }

   @Override
   public String getSubMenu() {
      return "";
   }

   @Override
   public void onPluginSelected() {
         try {
            System.out.println(studio_);
            System.out.println(studio_.getSnapLiveManager());
            System.out.println(studio_.getSnapLiveManager().getDisplay());
//            System.out.println(studio_.getSnapLiveManager().getDisplay().duplicate());
            System.out.println(studio_.getSnapLiveManager().getDisplay().getDisplaySettings());
            System.out.println(studio_.getSnapLiveManager().getDisplay().getDataProvider());
            System.out.println(studio_.getSnapLiveManager().getDisplay().getDisplaySettings().getAllChannelSettings());

            System.out.println(studio_.getSnapLiveManager().getDisplay().duplicate());
//            studio_.getDisplayManager().createDisplay()
//            DisplayWindow displayWindow = studio_.getSnapLiveManager().getDisplay().duplicate();


            //            FFTViewer fftviewer = new FFTViewer(studio_);
//            studio_.events().registerForEvents(fftviewer);
         } catch (Exception ex) {
            if (studio_ != null) {
               studio_.logs().logError(ex);
            }
         }
      }
//   }
//}
   @Override
   public String getCopyright() {
      return COPYRIGHT_NOTICE;
   }

   @Override
   public String getHelpText() {
      return DESCRIPTION;
   }

   @Override
   public String getName() {
      return NAME;
   }

   @Override
   public String getVersion() {
      return VERSION_INFO;
   }




}
//        Datastore store = studio_.data().createRAMDatastore();
//        DisplayWindow live = studio_.displays().createDisplay(store);
//        List<Image> images = studio_.live().snap(false);
//        System.out.print("imageget");
//        Image image = images.get(0);
////      Datastore store = studio_.displays().show(image);
//        Coords.CoordsBuilder builder = studio_.data().getCoordsBuilder();
//        builder = builder.time(0).channel(0);
//        image = image.copyAtCoords(builder.build());
//        System.out.print("putimage");
//        try {
//        store.putImage(image);
//        } catch (DatastoreFrozenException e) {
//        e.printStackTrace();
//        } catch (DatastoreRewriteException e) {
//        e.printStackTrace();
//        }
//        System.out.print("onPluginSelected2");
//        studio_.logs().logMessage("onPluginSelected");
//        try {
//        studio_.logs().logMessage("onPluginSelected");
//        } catch (Exception ex) {
//        if (studio_ != null) {
//        studio_.logs().logError(ex);
//        }