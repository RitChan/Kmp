import java.util.*;

class KmpMatcher {
	private String pattern;
	private int[] pi;
	
	KmpMatcher(){}
	KmpMatcher( String pattern ){
		this.pattern = pattern;
		setPi();
	}

	
	public void setPattern( String newPattern ) {
		pattern = newPattern;
		setPi();
	}
	
	public int[] execute( char[] t ) {
		char[] p = pattern.toCharArray();
		ArrayList<Integer> al = new ArrayList<Integer>();
		int q=-1;
		for(int i=0; i<t.length; i++) {
			while( q>-1 && p[q+1]!=t[i] )
				q = pi[q];
			if( p[q+1]==t[i] )
				q++;
			if( q==p.length-1 ) {
				al.add( new Integer(i-q-1) );
				q = pi[q];
			}
		}
		int[] shift = toArray( al );
		return shift;
	}
	
	public int[] execute( String t ) {
		return execute( t.toCharArray() );
	}
	
	private int[] toArray( ArrayList<Integer> l ) {
		ListIterator<Integer> iterator = l.listIterator();
		int[] i = new int[l.size()];
		for(int k=0; k<i.length; k++) {
			i[k] = iterator.next().intValue();
		}
		return i;
	}
	
	private void setPi() {
		char[] p = pattern.toCharArray();
		pi = new int[ p.length ];
		pi[0] = -1;
		int k = -1;
		for( int i=1; i<pi.length; i++) {
			while( k>-1 && p[k+1]!=p[i] )
				k = pi[k];
			if( p[k+1]==p[i] )
				k++;
			pi[i] = k;
		}
	}
}

