package spiderweb;


/**
 * Write a description of class SpiderWebContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpiderWebContest
{
    /**
     * Método que hace la simulación final.
     * @param strands es el numero total de hilos que tiene la telaraña.
     * @param favorite, es el hilo favorito de la araña.
     * @param bridges, son los puentes a crear, su tipo es un arreglo de enteros multidimensional
     * @param strand, es el hilo donde comenzara el recorrido.
     * @return devuelve la solucion del ejercicio de la maraton.
     */
    public void simulate(int strands, int favorite, int[][] bridges, int strand){
        favoriteStrand = favorite;
        spiderweb = new spiderWeb(strands, favorite, bridges);
        accessCoordenadasPuentes();
        unusedBridges = spiderweb.getUnusedBridges();
        int[][] datosPuentes = DisCam();
        int totalCaminos = spiderweb.getStrandNumber();
        Araña araña = spiderweb.getAraña();
        int ultimoCamino = strand;
        for (int i = 0; i < datosPuentes.length; i++) { 
            int distancia = datosPuentes[i][0];
            int camino1 = datosPuentes[i][1];
            int camino2 = datosPuentes[i][2];
            double distanciaAraña = araña.traductorSpotsInverso();
            if ((ultimoCamino == camino1 || ultimoCamino == camino2) && distanciaAraña < distancia) {
                if (ultimoCamino == camino1) {
                    int[] coord = {distancia, camino1};
                    if (unusedBridges.containsValue(coord)){
                        int[] coordenadas = traductor(distancia, camino2, totalCaminos);
                        int x = coordenadas[0];
                        int y = coordenadas[1];
                        spiderweb.spiderWalk(x, y);
                        ultimoCamino = camino2;
                        int[] valorAEliminar = {distancia, camino1};
                        Iterator<Map.Entry<String, int[]>> iterator = unusedBridges.entrySet().iterator();
                        while (iterator.hasNext()) {
                            Map.Entry<String, int[]> entry = iterator.next();
                            int[] valorActual = entry.getValue();
                            if (Arrays.equals(valorActual, valorAEliminar)) {
                                iterator.remove();
                            }
                        }
                    }
                    
                } else {
                    int[] coordenadas = traductor(distancia, camino1, totalCaminos);
                    int x = coordenadas[0];
                    int y = coordenadas[1];
                    spiderweb.spiderWalk(x, y);
                    ultimoCamino = camino1;
                    int[] valorAEliminar = {distancia, camino2};   
                    Iterator<Map.Entry<String, int[]>> iterator = unusedBridges.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, int[]> entry = iterator.next();
                        int[] valorActual = entry.getValue();
                        if (Arrays.equals(valorActual, valorAEliminar)) {
                            iterator.remove();
                        }
                    }
                }
                
            }
        }
        
        int distanciaMasCorta;
        if (Math.abs(ultimoCamino - favoriteStrand) > (strands / 2)) {
            distanciaMasCorta = strands - Math.abs(ultimoCamino - favoriteStrand);
        } else {
            distanciaMasCorta = Math.abs(ultimoCamino - favoriteStrand);   
        }
    }
}    
