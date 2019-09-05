package org.npl.biomet.mmsim;

import ij.process.FHT;
import ij.process.ImageProcessor;
import org.micromanager.Studio;
import org.micromanager.data.Image;
import org.micromanager.data.ImageJConverter;

public class FFT {

    private ImageJConverter ijconverter;

    public FFT(Studio studio_){
        ijconverter = studio_.data().getImageJConverter();
    }

    public Image doFFT(Image image){
        
//		System.out.println("Starting fft");
        Image raw_image = image;
        Image out;

        ImageProcessor raw_image_ip = ijconverter.createProcessor(raw_image);
        FHT fht = new FHT(raw_image_ip);
        fht.setShowProgress(false);
        if(fht.powerOf2Size()){
            fht.transform();
            ImageProcessor fft_processor = fht.getPowerSpectrum();
            ImageProcessor out_ip = fft_processor.convertToShort(false);
            out = ijconverter.createImage(out_ip, image.getCoords(), image.getMetadata());

        }
        else{
            out = null;
//				live_display.setCustomTitle("FFT error");
        }

        return out;
    }
}
