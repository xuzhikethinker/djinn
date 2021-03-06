/*
 * Created on Mar 18, 2006
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
package net.jnovation.djinn.util;

public class DjinnException extends Exception {

    /** Comment for <code>serialVersionUID</code> */
    private static final long serialVersionUID = 8832140650754521785L;

    public DjinnException(String message) {
        super(message);
    }

    public DjinnException(String message, Throwable cause) {
        super(message, cause);
    }

}
