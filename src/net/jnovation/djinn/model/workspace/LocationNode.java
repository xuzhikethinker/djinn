/*
 * Created on Feb 27, 2006
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
package net.jnovation.djinn.model.workspace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;

import net.jnovation.djinn.db.data.Location;
import net.jnovation.djinn.db.data.Package;
import net.jnovation.djinn.db.mgmt.ConnectionManager;
import net.jnovation.djinn.db.mgmt.QueryHelper;
import net.jnovation.djinn.db.mgmt.RowConverter;
import net.jnovation.djinn.i18n.Images;

public class LocationNode extends DBTreeNode {
    
    private static final Icon ICON = Images.getIcon("Jar.icon");
    
    public LocationNode(ProjectNode parent, Location location) {
        super(parent, location);        
    }
    
    @Override
    protected Vector<DBTreeNode> getChildren() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        Vector<DBTreeNode> children = new Vector<DBTreeNode>();
        QueryHelper<PackageNode> queryHelper = new QueryHelper<PackageNode>();
        List<PackageNode> projectNodeList = queryHelper.executeQuery(conn,
                "SELECT * FROM PACKAGES WHERE location_key = " + getDataObject().getKey(), 
                new RowConverter<PackageNode>(){
            public PackageNode getRow(ResultSet rs) throws SQLException {                
                Package packageObj = new Package(rs);                        
                PackageNode packageNode = new PackageNode(LocationNode.this, packageObj);
                return packageNode;
            }            
        });
        children.addAll(projectNodeList);
        return children;
    }

    public Icon getIcon() {
        return ICON;
    }  
    
}
