/*
 * Created on Feb 5, 2006
 * By Fabien Benoit - http://www.jnovation.net 
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.jnovation.djinn;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import net.jnovation.djinn.bytecode.importer.ImportListener;
import net.jnovation.djinn.bytecode.importer.RecursiveDirectoryImporter;
import net.jnovation.djinn.control.Application;
import net.jnovation.djinn.db.mgmt.ConnectionManager;
import net.jnovation.djinn.i18n.Messages;
import net.jnovation.djinn.util.DjinnException;
import net.jnovation.djinn.util.FileChooserFactory;
import net.jnovation.djinn.util.SwingWorker;

public class Launcher {
    
    public static void main(String[] args) {       
            	    	
    	// Install the look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) { 
            e.printStackTrace();
        }
        
        // Verify the call syntax
    	if (args.length > 1) {    		
    		JOptionPane.showMessageDialog(null, Messages.getString("error.argsyntax"), 
    				Messages.getString("error"), JOptionPane.ERROR_MESSAGE);    		
    		System.exit(2);
    	}
    	if (args.length == 1) {
    		final File directoryToImport = new File(args[0]);
    		if (!directoryToImport.isDirectory()) {
	    		JOptionPane.showMessageDialog(null, Messages.getString("error.argsyntax"), 
	    				Messages.getString("error"), JOptionPane.ERROR_MESSAGE);
	    		System.exit(2);
    		}
    		
    		SwingWorker importWorker = new SwingWorker(true) {
				@Override
				public Object construct() {
										
					RecursiveDirectoryImporter importer = new RecursiveDirectoryImporter(directoryToImport);
					importer.addImportListener(new ImportListener() {
						public void updateImportProgress(int percent) {
							updateProgress(percent);
						}
						public void updateImportCurrentStatus(String status) {
							updateMessage(status);		
						}                    	
                    });
					
					try {
						importer.importProject();
					} catch (DjinnException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
				}    			
				
    		};
    		importWorker.start();
    		
    	}    	
    	        
        FileChooserFactory.getInstance();
        ConnectionManager.getInstance();
        
        Application.getInstance().start();
        
    }
    
}
