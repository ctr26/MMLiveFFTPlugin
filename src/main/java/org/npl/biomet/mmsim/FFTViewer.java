package org.npl.biomet.mmsim;

//import com.sun.org.apache.xpath.internal.operations.Bool;
import com.google.common.primitives.Doubles;
import ij.process.FHT;

import ij.process.FHT;
import ij.process.FloatProcessor;
import ij.ImagePlus;
import ij.plugin.FFT;
import ij.process.ImageProcessor;
import ij.process.ShortProcessor;
import org.jtransforms.fft.FloatFFT_2D;
import org.micromanager.SnapLiveManager;
import org.micromanager.Studio;
import org.micromanager.data.*;
import org.micromanager.display.DisplayWindow;
import org.micromanager.events.LiveModeEvent;
import org.micromanager.display.DataViewer;
import com.google.common.eventbus.Subscribe;
import org.micromanager.data.ImageJConverter;
import org.micromanager.events.internal.DefaultLiveModeEvent;

import java.io.IOException;

//import org.micromanager.internal.SnapLiveManager;
public class FFTViewer{
	private final Studio studio_;
	private final SnapLiveManager live_;

	public DisplayWindow fft_display;
	public Datastore fft_store;
	private ImageJConverter ijconverter;
	private DisplayWindow live_display;
	private FloatFFT_2D floatFFT_2D;

	private Image current_image;
//	private float[][] fft;

	public FFTViewer(Studio studio) {
		this(studio, null);
	}
	public FFTViewer(Studio studio, DisplayWindow displayWindow){
		studio_ = studio;
		ijconverter = studio_.data().getImageJConverter();
		live_ = studio_.live();
		fft_store = studio_.data().createRewritableRAMDatastore();
		fft_display = studio_.displays().createDisplay(fft_store);
		live_display = displayWindow;

		studio_.events().post(new DefaultLiveModeEvent(true));
	}

	@Subscribe
	public void onLiveModeEvent(LiveModeEvent ase) {

		boolean isOnLive = ase.getIsOn();

		if (live_.getIsLiveModeOn()) {
			System.out.println("Live on");
			live_display = studio_.live().getDisplay();
//			live_display.duplicate();
//			studio_.events().registerForEvents(this);
			DataProvider live_provider = live_display.getDataProvider();

			try {
				floatFFT_2D = new FloatFFT_2D(live_provider.getAnyImage().getWidth(), live_provider.getAnyImage().getHeight());
				live_provider.registerForEvents(this);
			} catch (IOException e) {
				e.printStackTrace();
				floatFFT_2D=null;
			}


		} else {
			System.out.println("Live off");
//			live_provider.unregisterForEvents(this);
		}
	}

	@Subscribe
	public void onNewLiveImage(DataProviderHasNewImageEvent e){
//		System.out.println("New image");
		current_image = e.getImage();
//		System.out.println(current_image.getCoords());

////		fft_display.
		try {
//			store = studio_.data().createRAMDatastore();
//			fft_store.putImage(current_image);
			fft_store.putImage(doFFT(current_image));
//			fft_display = studio_.displays().createDisplay(store);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}


	public Image doFFT(Image image){
//		System.out.println("Starting fft");

		Image raw_image = image;
		ImageProcessor raw_image_ip = ijconverter.createProcessor(raw_image);
		FHT fht = new FHT(raw_image_ip);
		fht.transform();
		ImageProcessor fft_processor = fht.getPowerSpectrum();

//		float[][] fft = raw_image_ip.getFloatArray();
//		floatFFT_2D.realForward(fft);
//		FloatProcessor fft_processor = new FloatProcessor(fft);
//		fft_float_processor.log();s
//		fft_float_processor.resetMinAndMax();
		ImageProcessor out_ip = fft_processor.convertToShort(false);
//		out_ip.resetMinAndMax();

		Image out = ijconverter.createImage(out_ip, image.getCoords(), image.getMetadata());
//		Image raw_image_ip_image = ijconverter.createImage(raw_image_ip, image.getCoords(), image.getMetadata());
//		System.out.println("fft_float_processor");
		return out;
	}
}
