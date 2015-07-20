package view.renderers;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Klasa TreeRenderer omogucava razlicit graficki prikaz elemenata u stablu: paket, tabela i kolona.
 * @author Ratomir
 *
 */
public class TreeRenderer extends DefaultTreeCellRenderer
{

	private static final long serialVersionUID = 1L;
	
	Icon databaseIcon = new ImageIcon("icons/explorer/database.png");
	Icon packageIcon = new ImageIcon("icons/explorer/package.png");
	Icon tableIcon = new ImageIcon("icons/explorer/table.png");
	Icon columnIcon = new ImageIcon("icons/explorer/column_24.png");
	
	Icon openIcon = new ImageIcon("icons/explorer/open.png");
	Icon colseIcon = new ImageIcon("icons/explorer/close.png");
	
	public Component getTreeCellRendererComponent
	(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	
		if (tree.getModel().getRoot() == value) 
		{
			setIcon(databaseIcon);
		}
		else if(value instanceof TreeElement.Package)
		{
			setIcon(packageIcon);
		}
		else if(value instanceof TreeElement.Table)
		{
			setIcon(tableIcon);
		}
		else if(value instanceof TreeElement.Column)
		{
			setIcon(columnIcon);
		}
		
//		if(expanded)
//		{
//			setOpenIcon(openIcon);
//		}
//		else
//		{
//			setClosedIcon(closedIcon);
//		}
		
//		
//		
////		setLeafIcon(loadIcon(((BaseNode) value).getLeafIconResourceFilename()));
//		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		return this;
	}

}
