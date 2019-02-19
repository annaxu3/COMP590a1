package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import io.OutputStreamBitSink;

public class HuffEncode {

	public static void main(String[] args) throws IOException {
		// String input_file_name = "data/uncompressed.txt";
		String input_file_name = "data/reuncompressed.txt";
		String output_file_name = "data/recompressed.txt";

		FileInputStream fis = new FileInputStream(input_file_name);

		int[] symbol_counts = new int[256];
		int num_symbols = 0;
		
		// Read in each symbol (i.e. byte) of input file and 
		// update appropriate count value in symbol_counts
		// Should end up with total number of symbols 
		// (i.e., length of file) as num_symbols
		
		while (fis.available() > 0) {
			int nextByte = fis.read();
			symbol_counts[nextByte]++;
			num_symbols++;
		}

		// Close input file
		fis.close();

		// Create array of symbol values
		
		int[] symbols = new int[256];
		for (int i=0; i<256; i++) {
			symbols[i] = i;
		}

		// Create encoder using symbols and their associated counts from file.
		
		HuffmanEncoder encoder = new HuffmanEncoder(symbols, symbol_counts);

		// Open output stream.
		FileOutputStream fos = new FileOutputStream(output_file_name);
		OutputStreamBitSink bit_sink = new OutputStreamBitSink(fos);
		
		// Write out code lengths for each symbol as 8 bit value to output file.
		for (int i = 0; i < 256; i++) {
			bit_sink.write(encoder.getCode(i).length(), 8);
			// System.out.println(encoder.getCode(i).length());
		}
		
		// Write out total number of symbols as 32 bit value.
		bit_sink.write(num_symbols, 32);

		// Reopen input file.
		fis = new FileInputStream(input_file_name);

		// Go through input file, read each symbol (i.e. byte),
		// look up code using encoder.getCode() and write code
        // out to output file.
		for (int j = 0; j < num_symbols; j++) {
			int next_byte = fis.read();
			bit_sink.write(encoder.getCode(next_byte));
		}

		// Pad output to next word.
		bit_sink.padToWord();

		// Close files.
		fis.close();
		fos.close();
		
//		System.out.println("done");
	}
}