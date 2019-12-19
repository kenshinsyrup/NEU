package assignment2_refactored.recommend;

import assignment2_refactored.furniture.accessory.AbstractAccessory;
import assignment2_refactored.furniture.cabinet.AbstractCabinet;
import assignment2_refactored.furniture.config.AbstractMount;
import assignment2_refactored.furniture.config.Fit;
import assignment2_refactored.furniture.config.Size;
import assignment2_refactored.furniture.part.AbstractPart;
import assignment2_refactored.furniture.part.Door;
import assignment2_refactored.furniture.part.Drawer;
import assignment2_refactored.utils.Num;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Represents a recommend.
 */
public class Recommend {

  /**
   * Inits a recommend.
   *
   * @param criteria is given customer criteria.
   * @return true is successful.
   */
  public boolean recommend(Criteria criteria) {
    int totalWidth = criteria.getCabinetWidth();
    int cabinetNum = totalWidth / Num.THIRTY_SIX;

    Set<AbstractCabinet> abstractCabinets = recommendCabinets(criteria);

    JSONObject recommendation = new JSONObject();
    JSONArray cabinetJSONArray = new JSONArray();
    for (AbstractCabinet abstractCabinet : abstractCabinets) {
      Set<AbstractPart> abstractParts = getUsableParts(abstractCabinet, AbstractPart.partStock());
      cabinetJSONArray.add(buildJSON(abstractCabinet, cabinetNum, abstractParts));
    }
    recommendation.put("cabinets", cabinetJSONArray);

    //Write JSON file
    try (FileWriter file = new FileWriter("order.json")) {

      file.write(recommendation.toJSONString());
      file.flush();

    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  private Set<AbstractPart> getUsableParts(AbstractCabinet abstractCabinet,
      Set<AbstractPart> abstractParts) {
    Iterator<AbstractPart> iterator = abstractParts.iterator();
    while (iterator.hasNext()) {
      AbstractPart abstractPart = iterator.next();
      boolean usable = true;

      // must has 18 drawer
      if (abstractCabinet.getSize().getDepth() >= Num.EIGHTEEN) {
        if (abstractPart instanceof Drawer && abstractPart.getSize().getDepth() == Num.EIGHTEEN) {
          continue;
        }
      }

      if (abstractPart instanceof Drawer && abstractCabinet.getFits() != null) {
        boolean needDrawer = false;
        for (Fit fit : abstractCabinet.getFits()) {
          if (fit.getDrawerNum() != 0) {
            needDrawer = true;
          }
        }
        if (!needDrawer) {
          usable = false;
        }
      }

      if (abstractCabinet.getSize().getWidth() < abstractPart.getSize().getWidth()) {
        usable = false;
      }

      if (!usable) {
        iterator.remove();
      }
    }

    return abstractParts;
  }

  private JSONObject buildJSON(AbstractCabinet abstractCabinet, int cabinetNum,
      Set<AbstractPart> abstractParts) {
    JSONObject cabinetJSONObj = new JSONObject();
    cabinetJSONObj.put("cabinet_name", abstractCabinet.getName());
    cabinetJSONObj.put("cabinet_num", cabinetNum);

    // corner feet num
    int num = cabinetNum;
    if (num % 2 == 0) {
      num -= 1;
    }
    int cornerFeetNum = 4 + num / 2 * 2;
    cabinetJSONObj.put("corner_feet_num", cornerFeetNum);

    // size
    cabinetJSONObj.put("size", getSizeJSONObj(abstractCabinet.getSize()));

    // mounts
    JSONArray mountJSONArr = new JSONArray();
    for (AbstractMount mount : abstractCabinet.getMounts()) {
      mountJSONArr.add(getMountJSONObj(mount));
    }
    cabinetJSONObj.put("mounts", mountJSONArr);

    // fits
    JSONArray fitsJSONArr = new JSONArray();
    for (Fit fit : abstractCabinet.getFits()) {
      fitsJSONArr.add(getFitJSONObj(fit));
    }
    cabinetJSONObj.put("fits", fitsJSONArr);

    // colors
    JSONArray colorJSONArr = new JSONArray();
    for (String color : abstractCabinet.getColors()) {
      colorJSONArr.add(color);
    }
    cabinetJSONObj.put("colors", colorJSONArr);

    // parts
    JSONObject doorPartJSONObj = new JSONObject();
    JSONArray doorPartJSONArr = new JSONArray();
    JSONObject drawerPartJSONObj = new JSONObject();
    JSONArray drawerPartJSONArr = new JSONArray();
    for (AbstractPart abstractPart : abstractParts) {
      if (abstractPart instanceof Door) {
        doorPartJSONArr.add(getPartJSONObj(abstractPart));
      } else if (abstractPart instanceof Drawer) {
        drawerPartJSONArr.add(getPartJSONObj(abstractPart));
      }
    }
    doorPartJSONObj.put("doors", doorPartJSONArr);
    drawerPartJSONObj.put("drawers", drawerPartJSONArr);
    JSONArray partJSONArr = new JSONArray();
    partJSONArr.add(doorPartJSONObj);
    partJSONArr.add(drawerPartJSONObj);
    cabinetJSONObj.put("parts", partJSONArr);

    // wall fixture
    if (abstractCabinet.getWallFixture() != null) {
      cabinetJSONObj.put("wall_fixture", getAccessoryJSONObj(abstractCabinet.getWallFixture()));
    }

    // wall mount rail
    if (abstractCabinet.getWallMountRail() != null) {
      cabinetJSONObj
          .put("wall_mount_rail", getAccessoryJSONObj(abstractCabinet.getWallMountRail()));
    }

    return cabinetJSONObj;
  }

  private JSONObject getPartJSONObj(AbstractPart abstractPart) {
    JSONObject partJSONObj = new JSONObject();
    partJSONObj.put("name", abstractPart.getName());

    partJSONObj.put("size", getSizeJSONObj(abstractPart.getSize()));

    partJSONObj.put("num", abstractPart.getNum());

    JSONArray colorJSONArr = new JSONArray();
    for (String color : abstractPart.getColors()) {
      colorJSONArr.add(color);
    }
    partJSONObj.put("colors", colorJSONArr);

    if (abstractPart.getDoorHandle() != null) {
      partJSONObj.put("door_handle", getAccessoryJSONObj(abstractPart.getDoorHandle()));
    }
    if (abstractPart.getDrawerHandle() != null) {
      partJSONObj.put("drawer_handle", getAccessoryJSONObj(abstractPart.getDrawerHandle()));
    }

    return partJSONObj;
  }

  private JSONObject getAccessoryJSONObj(AbstractAccessory abstractAccessory) {
    JSONObject accessoryJSONObj = new JSONObject();
    accessoryJSONObj.put("is_required", abstractAccessory.isRequired());
    accessoryJSONObj.put("is_standard", abstractAccessory.isStandard());
    accessoryJSONObj.put("is_separate", abstractAccessory.isSeparate());
    accessoryJSONObj.put("num", abstractAccessory.getNum());
    return accessoryJSONObj;
  }

  private JSONObject getFitJSONObj(Fit fit) {
    JSONObject fitJSONObj = new JSONObject();
    fitJSONObj.put("shelf_num", fit.getShelfNum());
    fitJSONObj.put("drawer_num", fit.getDrawerNum());
    return fitJSONObj;
  }

  private JSONObject getMountJSONObj(AbstractMount mount) {
    JSONObject mountJSONObj = new JSONObject();
    mountJSONObj.put("floor_mounted", mount.isFloorMounted());
    mountJSONObj.put("feet_required", mount.isFeetRequired());
    mountJSONObj.put("feet_optional", mount.isFeetOptional());
    mountJSONObj.put("feet_separate", mount.isFeetSeparate());

    mountJSONObj.put("wall_mounted", mount.isWallMounted());
    mountJSONObj.put("rail_required", mount.isRailRequired());
    mountJSONObj.put("rail_standard", mount.isRailStandard());
    mountJSONObj.put("rail_separate", mount.isRailSeparate());
    return mountJSONObj;
  }

  private JSONObject getSizeJSONObj(Size size) {
    JSONObject sizeJSONObj = new JSONObject();
    sizeJSONObj.put("width", size.getWidth());
    sizeJSONObj.put("height", size.getHeight());
    sizeJSONObj.put("depth", size.getDepth());
    return sizeJSONObj;
  }


  // Apply the customer's criteria to furniture.cabinet stock to get all cabinets that is usable.
  private Set<AbstractCabinet> recommendCabinets(Criteria criteria) {
    Set<AbstractCabinet> abstractCabinets = AbstractCabinet.cabinetStock();

    Iterator<AbstractCabinet> iterator = abstractCabinets.iterator();
    while (iterator.hasNext()) {
      AbstractCabinet abstractCabinet = iterator.next();

      boolean usable = true;

      // check height
      if (criteria.getCabinetHeight() > abstractCabinet.getSize().getHeight()) {
        usable = false;
      }

      // check shelf num and drawer num
      boolean shelfEnough = false;
      boolean drawerEnough = false;
      for (Fit fit : abstractCabinet.getFits()) {
        if (criteria.getCabinetShelfNum() <= fit.getShelfNum()) {
          shelfEnough = true;
        }
        if (criteria.getCabinetDrawerNum() <= fit.getDrawerNum()) {
          drawerEnough = true;
        }
      }
      if (!shelfEnough || !drawerEnough) {
        usable = false;
      }

      // check colors
      if (criteria.getColors() != null) {
        boolean hasColor = false;
        for (String color : criteria.getColors()) {
          if (abstractCabinet.getColors().contains(color)) {
            hasColor = true;
          }
        }
        if (!hasColor) {
          usable = false;
        }
      }

      // check floor mount
      if (criteria.isFloorMounted()) {
        boolean hasFloorMounted = false;
        for (AbstractMount mount : abstractCabinet.getMounts()) {
          if (mount.isFloorMounted()) {
            hasFloorMounted = true;
          }
        }
        if (!hasFloorMounted) {
          usable = false;
        }
      }

      // check wall mount
      if (criteria.isWallMounted()) {
        boolean hasWallMounted = false;
        for (AbstractMount mount : abstractCabinet.getMounts()) {
          if (mount.isFloorMounted()) {
            hasWallMounted = true;
          }
        }
        if (!hasWallMounted) {
          usable = false;
        }
      }

      // if any condition in criteria is not satisfied, remove current furniture.cabinet
      if (!usable) {
        iterator.remove();
      }
    }

    return abstractCabinets;

  }

}

