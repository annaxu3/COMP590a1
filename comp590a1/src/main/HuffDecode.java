package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.InputStreamBitSource;
import io.InsufficientBitsLeftException;

public class HuffDecode {

	public static void main(String[] args) throws InsufficientBitsLeftException, IOException {
		// String input_file_name = "data/recompressed.dat";
		String input_file_name = "data/compressed.dat";
		String output_file_name = "data/reuncompressed.txt";
		
		FileInputStream fis = new FileInputStream(input_file_name);

		InputStreamBitSource bit_source = new InputStreamBitSource(fis);

		List<SymbolWithCodeLength> symbols_with_length = new ArrayList<SymbolWithCodeLength>();

		// Read in huffman code lengths from input file and associate them with each symbol as a 
		// SymbolWithCodeLength object and add to the list symbols_with_length.
		for (int i=0; i<256; i++) {
			int _codeLength = bit_source.next(8);
			// System.out.println(_codeLength);
			symbols_with_length.add(new SymbolWithCodeLength(i, _codeLength));
		}
	
		// Then sort they symbols.
		symbols_with_length.sort(null);
		
		// Now construct the canonical huffman tree

		HuffmanDecodeTree huff_tree = new HuffmanDecodeTree(symbols_with_length);

		// int num_symbols = 0;

		// Read in the next 32 bits from the input file the number of symbols
		int num_symbols = bit_source.next(32);
		// System.out.println("Number of Symbols: " + num_symbols);

		try {
			
			// Open up output file.
			
			FileOutputStream fos = new FileOutputStream(output_file_name);

			// int[] freqArray = new int[256];
			
			for (int i=0; i<num_symbols; i++) {
				
				// Decode next symbol using huff_tree and write out to file.

//				fos.write(huff_tree.decode(bit_source));
				int symbol = huff_tree.decode(bit_source);
				fos.write(symbol);
				
				
			
				// freqArray[symbol]++;

			}
			// System.out.println("frequency array: " + Arrays.toString(freqArray));

			// Flush output and close files.
			
			fos.flush();
			fos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
