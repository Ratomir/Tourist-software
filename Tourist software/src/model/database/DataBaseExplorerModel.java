package model.database;

import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import view.renderers.TreeElement;
/**
 * Klasa DataBaseExplorerModel predstavlja model stabla i obezbjedjuje  metode za ucitavanje strukture baze.
 * Moguce je pribaviti i opis kolone na osnovu proslijedjenog koda tabele.
 * 
 * @author Ivana
 *
 */
public class DataBaseExplorerModel extends DefaultTreeModel 
{
	private static final long serialVersionUID = 1L;
	
	private Document document;
	private XPathExpression expression;
	private XPath xpath;
	private TreeElement root;
	
	/**
	 * Metoda ucitava strukturu baze u obliku stabla iz putanje datoteke koja sadrzi opis.
	 * Moguce je ucitati podatke o paketima, tabelama i kolonama u bazi.
	 * 
	 * @param rootElement - korijen element stabla
	 * @param path - putanja do datoteke iz koje se ucitava baza
	 */
	public DataBaseExplorerModel(TreeNode rootElement, String path) 
	{
		super(rootElement);
		try 
		{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();	
			document = builder.parse(path);	
			XPathFactory factory = XPathFactory.newInstance();
			xpath = factory.newXPath();
	
			expression = xpath.compile("/database");
			Node database = (Node) expression.evaluate(document, XPathConstants.NODE);
			
			root = new TreeElement.Package();
			root.setCode(null); // Baza ima samo naziv
			root.setName(database.getAttributes().getNamedItem("name").getNodeValue());
			
			expression = xpath.compile("package");
			NodeList packages = (NodeList) expression.evaluate(database, XPathConstants.NODESET);
			
			subPackages(packages, root);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Doslo je do greske!\nOpis: " + e.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
		} 
	}
	/**
	 * Metoda omogucava smjestanje liste elemenata unutar roditeljskog elementa.
	 * 
	 * @param packages - lista paketa
	 * @param rootPackage-  root paket (roditeljski paket)
	 * @throws XPathExpressionException
	 */
	private void subPackages(NodeList packages, TreeElement rootPackage) throws XPathExpressionException 
	{
		//
		// Paketi
		//
		for (int i = 0; i < packages.getLength(); i++) 
		{
			TreeElement.Package tmpPackage = new TreeElement.Package();
			tmpPackage.setCode(null); // Paket ima samo naziv
			tmpPackage.setName(packages.item(i).getAttributes().getNamedItem("name").getNodeValue());
			rootPackage.addElement(tmpPackage);
			
			expression = xpath.compile("package");
			NodeList subpacks = (NodeList) expression.evaluate(packages.item(i), XPathConstants.NODESET);
			
			subPackages(subpacks, tmpPackage);
			
			expression = xpath.compile("table");
			NodeList tables = (NodeList) expression.evaluate(packages.item(i), XPathConstants.NODESET);
			
			//
			// Tabele
			//
			for (int j = 0; j < tables.getLength(); j++) 
			{
				TreeElement.Table tmpTable = new TreeElement.Table();
				
				tmpPackage.addElement(tmpTable);
				
				tmpTable.setCode(tables.item(j).getAttributes().getNamedItem("code").getNodeValue());
				tmpTable.setName(tables.item(j).getAttributes().getNamedItem("name").getNodeValue());
				
				expression = xpath.compile("column");
				NodeList columns = (NodeList) expression.evaluate(tables.item(j), XPathConstants.NODESET);
				
				//
				// Kolone
				//
				for (int k = 0; k < columns.getLength(); k++) 
				{	
					TreeElement.Column tmpColumn = new TreeElement.Column();
					
					tmpColumn.setCode(columns.item(k).getAttributes().getNamedItem("code").getNodeValue());
					tmpColumn.setName(columns.item(k).getAttributes().getNamedItem("name").getNodeValue());
					
					tmpColumn.setNullable(columns.item(k).getAttributes().getNamedItem("nullable").getNodeValue().equalsIgnoreCase("true"));
					tmpColumn.setPrimary(columns.item(k).getAttributes().getNamedItem("primary").getNodeValue().equalsIgnoreCase("true"));
					
					tmpColumn.setSize(columns.item(k).getAttributes().getNamedItem("size").getNodeValue());
					tmpColumn.setType(columns.item(k).getAttributes().getNamedItem("type").getNodeValue());
					tmpColumn.setGroup(columns.item(k).getAttributes().getNamedItem("group").getNodeValue());
					
					try
					{	
						expression = xpath.compile("references");
						NodeList refereneces = (NodeList) expression.evaluate(columns.item(k), XPathConstants.NODESET);
						
						tmpColumn.setRefTable(refereneces.item(0).getAttributes().getNamedItem("refTable").getNodeValue());
						tmpColumn.setRefColumn(refereneces.item(0).getAttributes().getNamedItem("refColumn").getNodeValue());
					}
					catch(Exception e){}
					
					tmpTable.addElement(tmpColumn);
				}
				
				//
				// Procedure
				//
				expression = xpath.compile("crud/create");
				Node create = (Node) expression.evaluate(tables.item(j), XPathConstants.NODE);
				tmpTable.setCreateSProc(create.getTextContent());
				
				expression = xpath.compile("crud/retrieve");
				Node retrieve = (Node) expression.evaluate(tables.item(j), XPathConstants.NODE);
				tmpTable.setRetrieveSProc(retrieve.getTextContent());
				
				expression = xpath.compile("crud/update");
				Node update = (Node) expression.evaluate(tables.item(j), XPathConstants.NODE);
				tmpTable.setUpdateSProc(update.getTextContent());
				
				expression = xpath.compile("crud/delete");
				Node delete = (Node) expression.evaluate(tables.item(j), XPathConstants.NODE);
				tmpTable.setDeleteSProc(delete.getTextContent());
			}
		}	
	}
	
	TreeElement.Table tbl = null;
	/**
	 * Metoda pretrazuje stablo i vraca tabelu sa zadatim kodom ako ona postoji u stablu.
	 * 
	 * @param code - kod tabele koju treba pronaci 
	 * @return - opis tabele ukoliko ona postoji
	 */
	public TreeElement.Table getTable(String code)
	{
		Vector<TreeElement> nodes = root.getAllElements();
		find(nodes, code);	
	    
	    return tbl;
	}
	

	/**
	 * Rekurzivna metoda za pronalazenje tabele u stablu.
	 * 
	 * @param nodes - cvorovi koje je potrebno pretraziti
	 * @param code - kod tabele koja se trazi
	 */
	private void find(Vector<TreeElement> nodes, String code)
	{
		for(int i=0; i<nodes.size(); i++)
		{
			TreeElement el = nodes.get(i);
			if(el instanceof TreeElement.Package)
			{
				Vector<TreeElement> subNodes = el.getAllElements();
				find(subNodes, code);
			}
			else if(el instanceof TreeElement.Table)
			{
				if(((TreeElement.Table)el).getCode().equalsIgnoreCase(code))
				{
					tbl = ((TreeElement.Table)el);
				}
			}	
		}
	}
	
	/**
	 * Metoda vraca potomak elementa u stablu.
	 */
	@Override
	public Object getChild(Object parent, int index) 
	{
		if (parent instanceof TreeElement.Package) 
		{
			return ((TreeElement.Package)parent).getElementAt(index);
		}
		else if (parent instanceof TreeElement.Table) 
		{
			return ((TreeElement.Table)parent).getElementAt(index);
		}
		return null;
	}

	/**
	 * Metoda vraca broj potomaka zadatog elementa u stablu.
	 */
	@Override
	public int getChildCount(Object parent) 
	{
		if (parent instanceof TreeElement.Package) 
		{
			return ((TreeElement.Package)parent).getAllElements().size();
		}
		else if (parent instanceof TreeElement.Table) 
		{
			return ((TreeElement.Table)parent).getAllElements().size();
		}
		return 0;
	}
	
	/**
	 * Metoda vraca indeks potomka zadatog elementa.
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) 
	{
		if (parent instanceof TreeElement.Package) 
		{
			return ((TreeElement.Package)parent).getIndexOfElement((TreeElement) child);
		}
		else if (parent instanceof TreeElement.Table) 
		{
			return ((TreeElement.Table)parent).getIndexOfElement((TreeElement) child);
		}
		return -1;
	}
	
	/**
	 * Metoda vraca korijeni element.
	 */
	@Override
	public Object getRoot() 
	{
		return this.root;
	}
	
	/**
	 * Metoda vraca true - ako element nema potomke,
	 * false - ako ima.
	 */
	@Override
	public boolean isLeaf(Object node) 
	{
		return (node instanceof TreeElement.Column);
	}
}
